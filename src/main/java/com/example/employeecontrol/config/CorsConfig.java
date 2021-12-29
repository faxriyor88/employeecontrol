//package com.example.employeecontrol.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig {
//    @Bean
//    public WebMvcConfigurer configurer(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("https://empproba.herokuapp.com")
//                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS")
//                        .allowedHeaders("*");
//            }
//        };
//    }
//}
