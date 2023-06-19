package com.shopping.config;

import com.shopping.handler.LoginFailureHandler;
import com.shopping.handler.LoginSuccessHandler;
import com.shopping.handler.ShoppingLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity配置类
 */
@Configuration
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;


    /**
     * 处理请求
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    @Autowired
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()//禁止跨域伪造
                .cors()//启用Security的跨域访问,未放行请求需要跨域处理
           .and()
                .authorizeRequests()
                    .antMatchers("/book/*","/register")
                        .permitAll()//无需认证直接访问
                    .anyRequest()//任意请求
                        .authenticated()//必须认证才能访问
            .and()
                //登录配置
                .formLogin()
                    .loginProcessingUrl("/login")//指定登录处理器
                    .loginPage("http://localhost:8080/#/login")//使用重定向的方式跳转到登录页
                    //.failureUrl("/fail.html")//以重定向的方式跳转到失败页面
                    //.defaultSuccessUrl("http://localhost:8080")//以重定向的方式跳转到成功页面
                    .successHandler(new LoginSuccessHandler())//成功处理器，当登录成功会自动跳转到指定的处理器
                    .failureHandler(new LoginFailureHandler())//失败处理器
            .and()
                .logout()//退出配置
                    .logoutUrl("/exit")//退出的url地址
                        .invalidateHttpSession(true)//退出后销毁session
                        .logoutSuccessHandler(new ShoppingLogoutSuccessHandler())//退出成功后的处理器

                    ;
        return http.build();
    }


    /**
     * 注册认证处理器
     * @param builder
     * @throws Exception
     */
    @Autowired
    public void registerProvider(AuthenticationManagerBuilder builder) throws Exception {
        //创建认证处理器对象
     DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider()  ;
     //设置不隐藏UserNotFoundException异常
     daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
     daoAuthenticationProvider.setUserDetailsService(userDetailsService);//设置UserDetailsService对象
     daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());//设置密码编码器对象
        builder.authenticationProvider(daoAuthenticationProvider);//设置认证处理器对象
               /* .userDetailsService(userDetailsService)//设置UserDetailsService对象
                .passwordEncoder(new BCryptPasswordEncoder())//设置密码编码器对象*/

    }
}
