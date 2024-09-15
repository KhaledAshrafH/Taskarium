package com.springmvc.taskarium.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authRequest -> {
            authRequest.requestMatchers("/","/about","/styles/**","/h2-console/**","/register").permitAll();
            authRequest.requestMatchers("/update/**","/tasks/**","/notes/**","/","/task/**","/edit-task/**").authenticated();
            authRequest.requestMatchers("/users/**").hasRole("ADMIN");
        });
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(AbstractHttpConfigurer::disable);
        http.formLogin(login->{
            login.loginPage("/login");
            login.defaultSuccessUrl("/");
            login.failureUrl("/login?error");
            login.permitAll();
        });
        http.logout(logout->{
            logout.logoutSuccessUrl("/").permitAll();
        });
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
