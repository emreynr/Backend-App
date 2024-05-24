package com.crudapp.employee_backend_app.security;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

    @Bean

    // role based restrict
    /*
    GET   -> /api/employees/             -> READ ALL -> "EMPLOYEE"
    GET   -> /api/employees/{employeeId} -> READ ONE -> "EMPLOYEE"
    POST  -> /api/employees/             -> SAVE     -> "MANAGER"
    PUT   -> /api/employees/             -> UPDATE   -> "MANAGER"
    DELETE ->/api/employees/{employeeId} -> DELETE   -> "ADMIN"
     */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
