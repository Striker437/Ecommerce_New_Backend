package com.Ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ECommerceApplication {
	
	
	
	 @Bean public WebMvcConfigurer corsConfigurer() { return new
			  WebMvcConfigurer() { public void addCorsMapping(CorsRegistry corsRegistry) {
			  corsRegistry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").
			  allowedMethods("*").allowCredentials(true); }
			  
			  
			  }; }
			 

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}