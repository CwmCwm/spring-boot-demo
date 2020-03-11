package com.demo.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class DiyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    //自定义扩展 FilterInvocationSecurityMetadataSource 和 AccessDecisionManager
    //下面就是注入你实现的 FilterInvocationSecurityMetadataSource 和 AccessDecisionManager
    @Autowired
    private FilterInvocationSecurityMetadataSource diyFilterInvocationSecurityMetadataSource;

    @Autowired
    public void setAccessDecisionManager(AccessDecisionManager diyAccessDecisionManager) {
        super.setAccessDecisionManager(diyAccessDecisionManager);
    }


    /**
     * fi里面有一个被拦截的url
     * 里面调用DiyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
     * 再调用DiyAccessDecisionManager的decide方法来校验用户的权限是否足够
     * */
    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            // 执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.diyFilterInvocationSecurityMetadataSource;
    }


    //下面是 Filter接口的方法
    //Filter接口的doFilter方法，将方法关联调用
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        //invoke(fi); 就会执行上面代码
        invoke(fi);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
