package com.example.authserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by afa on 18.08.16.
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("bob").password("secret").roles("USER")
            .and()
            .withUser("ali").password("secret").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin().permitAll()
            .and()
            .authorizeRequests().anyRequest().authenticated();
    }
}
