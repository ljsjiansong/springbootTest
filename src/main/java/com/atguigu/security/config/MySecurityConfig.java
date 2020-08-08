package com.atguigu.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //定制请求规则，访问首页允许所有人可以访问
        http.authorizeRequests().antMatchers("/")
                .permitAll().antMatchers("/level1/**")
                .hasRole("VIP1").antMatchers("/level2/**")
                .hasRole("VIP2").antMatchers("/level3/**")
                .hasRole("VIP3");


        //开启登录功能
        http.formLogin().loginPage("/userlogin")
        .usernameParameter("user").passwordParameter("pwd")
        .loginPage("/userlogin");//如果没有权限就回到登录页面

        //开启注销功能
        http.logout().logoutSuccessUrl("/");

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");


    }


    @Override
    public void configure(AuthenticationManagerBuilder auth)throws Exception{
      //  auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("ljs").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2","VIP3");
     //   auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1","VIP3");
      //  auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1","VIP2");

        auth.inMemoryAuthentication().withUser("zhangsan")
                .password("123456").roles("VIP1","VIP2").
                and().withUser("lisi").password("123456")
                .roles("VIP1","VIP3").and().withUser("wangwu")
                .password("123456").roles("VIP2","VIP3");

    }





}

