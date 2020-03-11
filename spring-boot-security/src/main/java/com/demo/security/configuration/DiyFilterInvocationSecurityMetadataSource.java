package com.demo.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class DiyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource(name = "crmNamedParameterJdbcTemplate")
    NamedParameterJdbcTemplate crmNamedParameterJdbcTemplate;

    /**
     * 缓存权限数据
     * 数据结构
     * {
     *     "/order/get": ["customerService", "admin"],
     *     "/customer/get": ["customerService", "admin"],
     *     "/admin/get": ["admin"]
     * }
     */
    private HashMap<String, List<ConfigAttribute>> cacheMap = null;

    /**
     * 加载权限表中所有权限
     * 就是缓存数据
     */
    public void loadResourceDefine(){
        cacheMap = new HashMap<>();
        //crmApiRole  crmApi  crmRole  三表相连查询
        String sqlString = "select ar.apiRoleId, a.apiUrl, r.roleName from crmApiRole ar, crmApi a, crmRole r  where ar.apiId=a.apiId and ar.roleId=r.roleId";
        List<Map<String, Object>> apiRoles = crmNamedParameterJdbcTemplate.queryForList(sqlString, new HashMap<>());

        //封装数据放入cacheMap
        for(Map<String, Object> apiRole : apiRoles) {
            if (cacheMap.containsKey(apiRole.get("apiUrl").toString())) {
                List<ConfigAttribute> configAttributes = cacheMap.get(apiRole.get("apiUrl").toString());
                ConfigAttribute configAttribute = new SecurityConfig(apiRole.get("roleName").toString());
                configAttributes.add(configAttribute);
            }
            else {
                List<ConfigAttribute> configAttributes = new ArrayList<>();
                ConfigAttribute configAttribute = new SecurityConfig(apiRole.get("roleName").toString());
                configAttributes.add(configAttribute);
                cacheMap.put(apiRole.get("apiUrl").toString(), configAttributes);
            }
        }
    }

    /**
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，
     * 如果 cacheMap 没有找到对应数据，就返回null，
     * 之后进入 org.springframework.security.access.intercept.AbstractSecurityInterceptor 200行，看到rejectPublicInvocations 你就明白了 null会怎么判断
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(cacheMap == null) {
            loadResourceDefine();
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (Iterator<String> iter = cacheMap.keySet().iterator(); iter.hasNext();) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return cacheMap.get(resUrl);
            }
        }
        return null;
    }

    /**不关心该方法，用到自己去理解语义*/
    @Override
    public boolean supports(Class<?> class1) {
        return true;
    }

    /**不关心该方法，用到自己去理解语义*/
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }


}
