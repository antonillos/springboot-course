package com.bestapp;

import com.bestapp.usr.handlers.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler successHandler;

    @Autowired
    public void configurer(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication().withUser(users.username("admin").password("admin").roles("USER", "ADMIN"))
                .withUser(users.username("user").password("user").roles("USER"));
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/static/**").permitAll()
                .antMatchers("/form/**", "/matches/**")
                .hasAnyRole("ADMIN").anyRequest().authenticated()
                .and().formLogin().successHandler(successHandler).loginPage("/login").permitAll()
                .and().logout().permitAll()
                .and().exceptionHandling();
    }
}
