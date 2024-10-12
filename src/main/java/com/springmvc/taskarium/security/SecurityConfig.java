package com.springmvc.taskarium.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Configures Spring Security for the application.
     *
     * @param http The HttpSecurity object used to configure security filters.
     * @return The configured SecurityFilterChain.
     * @throws Exception If there's an issue configuring security.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Define URL authorization rules
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/about", "/styles/**", "/register")
                .permitAll()
                .requestMatchers("/update/**", "/tasks/**", "/notes/**", "/", "/task/**", "/edit-task/**")
                .authenticated()
                .requestMatchers("/users/**","/h2-console/**")
                .hasRole("ADMIN")
        );


        // Disable security features (for development only)
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(AbstractHttpConfigurer::disable);

        http.formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
        );

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Creates a bean for a password encoder.
     *
     * @return NoOpPasswordEncoder instance for demonstration purposes (replace with a secure encoder in production).
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
