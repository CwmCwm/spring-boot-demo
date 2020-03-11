package com.demo.security.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * org.springframework.security.core.userdetails.UserDetailsService 接口
 * spring-security提供的接口，实现接口就可以自定义扩展，用于账号密码验证，然后返回UserDetails
 *
 * org.springframework.security.core.userdetails.UserDetails 接口
 * spring-security提供的接口，实现接口可以自定义扩展用户信息，用户获取账号、密码、权限 等等，你需要什么就自己添加
 * 你可以参考一下spring帮你实现UserDetails接口的具体类
 *
 * */
@Component
public class DiyUserDetailsService implements UserDetailsService {

    //演示就不写Repository层，直接写sql语句了
    @Resource(name = "crmNamedParameterJdbcTemplate")
    NamedParameterJdbcTemplate crmNamedParameterJdbcTemplate;

    /**
     * 实现loadUserByUsername 方法获得 UserDetails
     * 重写方法loadUserByUsername 方法
     * 1 根据username查询数据表
     * 2 根据业务要求封装数据成UserDetails并返回，后面就可以通过UserDetails 来获取用户的信息
     *
     * 显然这个方法的语义没有包括密码验证
     * */
    @Override
    public UserDetails loadUserByUsername(String username) {
        Map<String, Object> crmCustomer;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("username", username);
            crmCustomer = crmNamedParameterJdbcTemplate.queryForMap("select * from crmCustomer where username=:username", paramMap);
        } catch (EmptyResultDataAccessException e) {
            //数据库查不到该账号
            //这里抛异常不会被全局异常处理器接收并处理，因为@ControllerAdvice 是处理控制器抛出的异常，这里还没有进入Controller层
            throw new UsernameNotFoundException("用户名不存在");
        }

        //用户对应的角色的列表。一个用户可以多个角色
        List<GrantedAuthority> authorities = new ArrayList<>();
        Map<String, Object> param1Map = new HashMap<>();
        param1Map.put("customerId", crmCustomer.get("customerId"));
        String sql1String = "select cr.customerRoleId, cr.customerId, cr.roleId, r.roleName from crmCustomerRole cr left join crmRole r on cr.roleId=r.roleId where cr.customerId=:customerId";
        List<Map<String, Object>> crmRoles = crmNamedParameterJdbcTemplate.queryForList(sql1String, param1Map);
        //这里使用SimpleGrantedAuthority， spring security提供的，能用就不用自己写
        for (Map<String, Object> crmRole : crmRoles) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(crmRole.get("roleName").toString());
            authorities.add(simpleGrantedAuthority);
        }

        //这里直接使用spring提供的 org.springframework.security.core.userdetails.User, 能用就不用自己写
        UserDetails userDetails = new User(crmCustomer.get("username").toString(), crmCustomer.get("password").toString(), authorities);

        return userDetails;
    }

}
