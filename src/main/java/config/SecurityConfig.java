//package config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    CorsConfigurationSource corsConfigurationSource() {
//        return request -> {
//          CorsConfiguration config = new CorsConfiguration();
//          config.setAllowedHeaders(Collections.singletonList("*"));
//          config.setAllowCredentials(true);
//          config.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS", "PUT", "DELETE"));
//          config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000"));
//          return config;
//        };
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
//                .csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//    }
//}
