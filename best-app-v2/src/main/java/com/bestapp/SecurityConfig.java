package com.bestapp;

import com.bestapp.usr.handlers.LoginSuccessHandler;
import com.bestapp.usr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler successHandler;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    public void configurer(AuthenticationManagerBuilder auth) throws Exception {

/*        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("select username,password,enabled from usr_users where username=?")
                .authoritiesByUsernameQuery("select u.username, a.name from usr_roles a "
                        + "inner join usr_users u on a.user_id = u.id where u.username=?");*/

        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder)
                .getUserDetailsService();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .and().formLogin().successHandler(successHandler).loginPage("/login").permitAll()
                .and().logout().permitAll()
                .and().exceptionHandling();
    }
}
