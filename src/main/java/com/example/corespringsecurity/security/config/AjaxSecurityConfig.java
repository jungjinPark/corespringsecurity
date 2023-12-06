package com.example.corespringsecurity.security.config;

import com.example.corespringsecurity.security.common.AjaxLoginAuthenticationEntryPoint;
import com.example.corespringsecurity.security.handler.AjaxAccessDeniedHandler;
import com.example.corespringsecurity.security.handler.AjaxAuthenticationFailureHandler;
import com.example.corespringsecurity.security.handler.AjaxAuthenticationSuccessHandler;
import com.example.corespringsecurity.security.provider.AjaxAuthenticationProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Slf4j
@Order(0)
public class AjaxSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
    @Autowired
    public AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
    @Autowired
    private AjaxAccessDeniedHandler ajaxAccessDeniedHandler;
    @Autowired
    private AjaxLoginAuthenticationEntryPoint ajaxLoginAuthenticationEntryPoint;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(ajaxAuthenticationProvider());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest().authenticated();

//        http
//                .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

        http
                .exceptionHandling()
                .authenticationEntryPoint(ajaxLoginAuthenticationEntryPoint) // UnAuthorized
                .accessDeniedHandler(ajaxAccessDeniedHandler) // Fobidden
        ;

        http
                .csrf().disable(); // API에 CSRF 정보 세팅 후 삭제
        
        customConfigurerAjax(http);
    }

    private void customConfigurerAjax(HttpSecurity http) throws Exception {
        http
                .apply(new AjaxLoginConfigurer<>())
                .successHandlerAjax(ajaxAuthenticationSuccessHandler)
                .failureHandlerAjax(ajaxAuthenticationFailureHandler)
                .loginProcessingUrl("/api/login")
                .setAuthenticationManager(authenticationManagerBean())
                .readAndWriteMapper(objectMapper)
        ;
    }

    @Bean
    public AjaxAuthenticationProvider ajaxAuthenticationProvider() {
        return new AjaxAuthenticationProvider();
    }
}
