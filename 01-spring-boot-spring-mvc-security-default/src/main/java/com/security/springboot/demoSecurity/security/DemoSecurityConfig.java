package com.security.springboot.demoSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
    // add support for JDBC ...
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        //tell spring security to use JDBC authentication with our data source
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //Next code is for our own table for roles and user

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?");

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()

        )
                .formLogin(form->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout->logout.permitAll()

                )
                .exceptionHandling(configurer->
                        configurer.accessDeniedPage("/access-denied")
                        );

        return http.build();
    }

/*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails hugo= User.builder()
                .username("hugo")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails karina= User.builder()
                .username("karina")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();


        UserDetails veronica= User.builder()
                .username("veronica")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(hugo,karina,veronica);
    }
 */
}













