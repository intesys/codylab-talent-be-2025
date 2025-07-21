package it.intesys.codylab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        try {
            http
                    .cors(Customizer.withDefaults()) // Use CORS configuration from WebConfig
                    .csrf(AbstractHttpConfigurer::disable)// Disable CSRF for simplicity, not recommended for production
                    .authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/v1/ciao/**").authenticated()
                            .anyRequest().permitAll())
                    .oauth2Login(Customizer.withDefaults());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return http.build();
    }
}