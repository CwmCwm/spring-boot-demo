package com.demo.security.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 继承 WebSecurityConfigurerAdapter 重写方法来自定义web security配置
 * 具体有哪些方法，这些方法提供哪些配置，见方法命名和源码上的注释咯，百度咯
 *
 * @EnableWebSecurity 启用web安全验证
 * */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 这里注入自定义实现的UserDetailsService类  DiyUserDetailsService
     * 下面会使用到
     */
    @Autowired
    UserDetailsService diyUserDetailsService;

    /**
     * 网上文章好多用 and ，直接一条一条配置更简洁明了
     * HttpSecurity 一堆配置，百度，用到了自己再看
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //添加静态目录/static 和 /login接口 为无需登录就能访问
        http.authorizeRequests().antMatchers( "/login", "/static/**").permitAll();
        //其他请求配置为需要登录才能访问
        http.authorizeRequests().anyRequest().authenticated();

        //登录验证方式，一般为 form表单，httpBasic
        //支持form表单提交验证方式
        http.formLogin().permitAll();
        //因为前后端分离，所以使用forward请求转发，虽说可以配置 failureHandler ，但算了不想添加额外概念
        http.formLogin().failureForwardUrl("/login/fail");
        //因为前后端分离，所以使用forward请求转发，虽说可以配置 successHandler ，但算了不想添加额外概念
        http.formLogin().successForwardUrl("/login/success");

        //支持httpBasic方式验证
        http.httpBasic();
        //登出接口 为无需登录就能访问
        http.logout().permitAll();

        //不使用csrf，百度一下csrf的使用场景去理解csrf
        http.csrf().disable();
    }

    /**
     * 重写方法，配置登录验证使用的自定义的 DiyUserDetailsService
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(diyUserDetailsService);
    }

    /**
     * 定义密码编码模式，这里定义为密码无加密，就是明文密码
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //是spring security5 默认支持多密码的模式
        //PasswordEncoderFactories.createDelegatingPasswordEncoder() 实例化一个多钟加密模式的编码编码器PasswordEncoder
        //使用这种方式，前端传输密码字段有原先 password=cwm  变为 password={noop}cwm
        //这个{} 大括号是必须，大括号里面就是密码模式，具体可以填哪些值见PasswordEncoderFactories.createDelegatingPasswordEncoder() 源码
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();

        //算了，我还是用最简单的，直接指定密码模式
        //想象一下场景，密码模式统一啊，没必要使用上面这个
        //idea中 ctrl+alt+b 查看有哪些PasswordEncoder 的实现类，直接实例化嘛，你想自定义自己的加密模式，自己实现PasswordEncoder接口
        return NoOpPasswordEncoder.getInstance();
    }

}
