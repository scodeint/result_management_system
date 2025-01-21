package com.sms.school_management_system.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Allow all requests to '/api/**' endpoints
                .allowedOrigins("http://localhost:5173")  // Allow frontend to access the backend
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow specific HTTP methods
                .allowCredentials(true)  // Allow cookies and credentials
                .allowedHeaders("*");  // Allow all headers
    }
}
