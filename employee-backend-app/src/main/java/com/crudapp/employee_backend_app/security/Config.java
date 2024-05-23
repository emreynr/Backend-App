package com.crudapp.employee_backend_app.security;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class Config {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails sarah = User.builder()
                .username("sarah")
                .password("{noop}21435342")
                .roles("EMPLOYEE")
                .build();

        UserDetails jack = User.builder()
                .username("jack")
                .password("{noop}5342152134")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails michael = User.builder()
                .username("michael")
                .password("{noop}87563345435")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(sarah, jack, michael);
    }
}
