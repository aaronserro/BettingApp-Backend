package com.betting.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// ...existing code...
import java.util.List;
import com.betting.backend.auth.JwtAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {
    /**
     * This class configures Spring Security, handles JWT auth, permits access to login endpoints and protects all other endpoints unless a valid token is provided
     * again this filter checks for a valid token on every request
     *
     */
    private final JwtAuthenticationFilter jwtAuthFilter;//this injects the Auth Filter for the JWT tokens
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {//Constructor for security config class
        this.jwtAuthFilter = jwtAuthFilter;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        /**
         * this is used internally to authenticate user credentials during login
         */
        return config.getAuthenticationManager();
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        /**
         * this method encrypts passwords to compare encrypter values
         */
        return new BCryptPasswordEncoder();
    }
    @Bean
public CorsConfigurationSource corsConfigurationSource() {
    /**
     * allows the frontend to access the backend and avoid CORS errors in the browser
     */
    CorsConfiguration configuration = new CorsConfiguration();//creating a new cors config obejct
    //setting all componsnets in the configuration
    configuration.setAllowedOrigins(List.of("http://localhost:5173"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    /**
     * this configures Spring Security to desable CSRF, enable CORS assign public/private routes stateless session and add JWT filter
     */
    http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))

        .authorizeHttpRequests(auth -> auth
            .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/auth/login", "/users/create", "/users").permitAll()
            .anyRequest().authenticated()

        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}
}
