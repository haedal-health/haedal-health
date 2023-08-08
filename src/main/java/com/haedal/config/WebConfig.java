package com.haedal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/booking/**")
////                .allowedOrigins("*")
//                .allowedOrigins("http://localhost:3000","http://localhost:8080",
//                        "http://localhost:8080/oauth2/authorization/kakao")
//                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept")
//                .exposedHeaders("JSESSIONID")
//                .exposedHeaders("headers")
//                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
//                .allowCredentials(true)
//                .maxAge(3000);
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000","http://localhost:8080","http://localhost:8080/booking/api/hello","https://localhost:3000","https://localhost:8080")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept")
                .exposedHeaders("headers")
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
                .allowCredentials(true)
                .maxAge(3000);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:8080","https://accounts.kakao.com/login"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
}