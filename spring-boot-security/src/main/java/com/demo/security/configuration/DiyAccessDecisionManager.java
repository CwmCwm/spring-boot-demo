package com.demo.security.configuration;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;


@Component
public class DiyAccessDecisionManager implements AccessDecisionManager {

    /**
     * decide 方法是判定是否拥有权限的决策方法，
     * authentication 是UserService中循环添加到 GrantedAuthority 对象中的权限信息集合.
     * object 包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     *
     * configAttributes 为DiyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
     */
    @Override
    public void decide(Authentication authentication, Object obj, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if(null == configAttributes || configAttributes.size() <= 0) {
            throw new AccessDeniedException("该用户没有权限");
        }
        ConfigAttribute configAttribute;
        String needRole;
        //两个循环
        for(Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
            configAttribute = iter.next();
            needRole = configAttribute.getAttribute();
            for(GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if(needRole.trim().equals(grantedAuthority.getAuthority())) {
                    return;
                }
            }
        }
        //这里抛AccessDeniedException 会被security框架捕获
        throw new AccessDeniedException("该用户没有权限");
    }

    /**不关心该方法，用到自己去理解语义*/
    @Override
    public boolean supports(ConfigAttribute configattribute) {
        return true;
    }

    /**不关心该方法，用到自己去理解语义*/
    @Override
    public boolean supports(Class<?> class1) {
        return true;
    }

}
