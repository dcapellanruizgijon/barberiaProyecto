package com.example.back;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // 🔥 PERMITIR MÉTODO DELETE EXPLÍCITAMENTE
                .requestMatchers(HttpMethod.DELETE, "/api/barberias/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/**").permitAll()
                
                // Tus reglas existentes
                .requestMatchers("/api/email/**").permitAll()
                .requestMatchers("/api/admin/**").permitAll()
                .requestMatchers("/api/incidencias/**").permitAll()
                .requestMatchers("/api/citas/**").permitAll()
                .requestMatchers("/api/imagenes/**", "/api/clientes/**", "/api/barberias/**", "/api/barberos/**", "/api/resenas/**").permitAll()
                .requestMatchers("/api/servicios/**", "/fotos/**", "/api/barberos/login/**", "/api/clientes/login/**").permitAll()
                .requestMatchers("/api/horarios/**", "/api/disponibilidad/**").permitAll()
                
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> basic.disable())
            .formLogin(form -> form.disable())
            .logout(logout -> logout.disable());
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}