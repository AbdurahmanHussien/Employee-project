package com.springboot.employeeproject.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/employees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/employees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/employees").hasRole("ADMIN")
                        .and()
                       .httpBasic(Customizer.withDefaults());
                        return http.build();
    }
}
