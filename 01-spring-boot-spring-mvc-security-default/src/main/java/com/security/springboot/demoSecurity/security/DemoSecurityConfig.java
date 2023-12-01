package com.security.springboot.demoSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails hugo= User.builder()
                .username("hugo")
                .password("{noop}test123")
                .roles("Employee")
                .build();

        UserDetails karina= User.builder()
                .username("karina")
                .password("{noop}test123")
                .roles("Employee","MANAGER")
                .build();


        UserDetails veronica= User.builder()
                .username("veronica")
                .password("{noop}test123")
                .roles("Employee","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(hugo,karina,veronica);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .anyRequest().authenticated()

        )
                .formLogin(form->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout->logout.permitAll()
                        );
        return http.build();
    }


}













