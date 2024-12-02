package org.blogsphere.blog.Configuration;

import org.blogsphere.blog.Component.AuthenticationEntryPointJwt;
import org.blogsphere.blog.Component.JwtAuthenticationFilter;
import org.blogsphere.blog.Service.UserSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    
    private final UserSecurityService userSecurityService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationEntryPointJwt authenticationEntryPointJwt;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(exception -> 
                exception.authenticationEntryPoint(authenticationEntryPointJwt)
            )
            .authorizeHttpRequests(
                request -> request
                    .requestMatchers(AUTHENTIFICATION_WHITE_LIST).permitAll()
                    .requestMatchers("/api/v1").permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement(manager -> 
                manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(
                jwtAuthenticationFilter, 
                UsernamePasswordAuthenticationFilter.class
            )
            .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(passwordEncoder);
        authenticationProvider.setUserDetailsService(userSecurityService);
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    private static final String[] AUTHENTIFICATION_WHITE_LIST = {
        "/api/v1/auth/**",
        "/api/v1/test/**",
        "/h2-console/**",
        "/api/v1/posts/**",
        "/api/v1/tags/**"
    };
}
