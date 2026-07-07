package com.timmy.swift_ship_api.config;


import com.timmy.swift_ship_api.auth.AuthUserDetailService;
import com.timmy.swift_ship_api.auth.JwtFilter;
import com.timmy.swift_ship_api.user.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtService jwtService;
    private final JwtFilter jwtFilter;
    private final AuthUserDetailService authUserDetailService;
    private final CustomSecurityProvider securityProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.cors((cors)->{})
        .csrf(csrf-> csrf.disable())
                .authorizeHttpRequests((auth)->
                        auth.requestMatchers("/api/v1/auth/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html",
                                        "/v3/api-docs/**",
                                        "/swagger-resources/**",
                                        "/webjars/**").permitAll()
                                .requestMatchers("/system-health/**").permitAll()
                                .anyRequest().authenticated()
                        )
                .sessionManagement((ssm)-> ssm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(securityProvider.authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:3001"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST","PUT", "PATCH", "DELETE", "OPTIONS"));

        corsConfiguration.setAllowedHeaders(List.of("*"));

        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
