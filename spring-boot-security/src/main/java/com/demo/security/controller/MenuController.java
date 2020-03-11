package com.demo.security.controller;


import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单接口，用户登录后，根据用户的角色和接口权限，返回对应的菜单
 * 这样就能根据不同角色显示不同的菜单
 * */
@Controller
public class MenuController {

    //演示就不写Repository层，直接写sql语句了
    @Resource(name = "crmNamedParameterJdbcTemplate")
    NamedParameterJdbcTemplate crmNamedParameterJdbcTemplate;

    /**
     * 用户登录后，调用该接口，该接口根据用户角色返回对应的菜单
     *
     * 根据数据表的数据，这个 /menu/listAll 是没有记录的
     * 那登录后的账号能访问这个 /menu/listAll 吗？
     * debug 进入 org.springframework.security.access.intercept.AbstractSecurityInterceptor 200行，看到rejectPublicInvocations 你就明白了
     * */
    @RequestMapping(value = "/menu/listAll")
    @ResponseBody
    public Object listAll() {
        //spring security提供静态方法来获取当前线程对应的用户信息
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        responseMap.put("username", user.getUsername());
        responseMap.put("authorities", user.getAuthorities());

        List<Map<String, Object>> crmMenus = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("roleName", grantedAuthority.getAuthority());
            String sqlString = "select mr.menuRoleId, m.menuName, m.parentMenuId, r.roleName from crmMenuRole mr, crmMenu m, crmRole r where mr.menuId=m.menuId and mr.roleId=r.roleId and r.roleName=:roleName ";
            crmMenus.addAll(crmNamedParameterJdbcTemplate.queryForList(sqlString, paramMap));
        }

        //TODO 这里就查出菜单，但没有排重和整理成树形结构
        responseMap.put("crmMenus", crmMenus);
        return responseMap;
    }

}
