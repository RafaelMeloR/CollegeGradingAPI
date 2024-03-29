
package com.vanier.grading_api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class ApplicationSecurityConfig {

        private static final String API_URL_PATTERN = "/**";

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspect) throws Exception {

        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspect);

        http.csrf(csrfConfigurer ->
                csrfConfigurer.ignoringRequestMatchers(mvcMatcherBuilder.pattern(API_URL_PATTERN),
                        PathRequest.toH2Console()));


        http.headers(headersConfigurer ->
                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        // Define the authorization needed to access each URL pattern
        http.authorizeHttpRequests(auth ->
                auth
                        // The request must match the URL pattern & User role
                        .requestMatchers(mvcMatcherBuilder.pattern(API_URL_PATTERN)).hasRole("ADMIN")

                        .requestMatchers(PathRequest.toH2Console()).authenticated()
                        .anyRequest().authenticated()
        );

        // Enable Login functionality (Web UI Login / Logout)
        http.formLogin(Customizer.withDefaults());

        http.httpBasic(Customizer.withDefaults());

        // Build the configuration we defined
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("student")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles("USER")
                .build());

        manager.createUser(User.withUsername("professor")
                        .password(bCryptPasswordEncoder.encode("professor"))
                        .roles("USER", "PROFESSOR")
                        .build());

        manager.createUser(User.withUsername("admin")
                .password(bCryptPasswordEncoder.encode("admin"))
                        .roles("USER", "PROFESSOR", "ADMIN")
                .build());

        return manager;
    }
        
}
