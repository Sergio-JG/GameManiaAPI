package com.tfg.restservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

@Configuration
class CorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:5173/")
				.allowedMethods("GET", "POST", "PUT", "DELETE").allowCredentials(true);
	}
}
