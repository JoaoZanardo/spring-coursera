package com.zanardo.todo.Configurations;

import com.zanardo.todo.filters.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authroize -> authroize
                        .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/todo/**")).hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(this.securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}