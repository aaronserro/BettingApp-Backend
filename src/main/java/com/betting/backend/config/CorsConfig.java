package com.betting.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //Marks the class as a config class
public class CorsConfig {
    /**
     * CORS means cross origin resouce Sharing for the backend.
     * CORS allows for the React frontend to talk to the Spring backend being blocked by the browser
     *
     */

    @Bean//This creates a Spring bean to apply the cors rules
    public WebMvcConfigurer corsConfigurer() {
        /**
         *
         * This class is a WebCOnfigurer for cors
         */
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")//applies cors rules to all endponts
                        .allowedOrigins("http://localhost:5173")//only allows requests from the React frontend server
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")//allows HTTP methods to be used across all origins
                        .allowedHeaders("*")//allows any headers in requests
                        .allowCredentials(true);//Allows the sending of cookies of JWT tokens
            }
        };
    }
}
