package com.tfg.restservice.config;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

@Configuration
public class MyConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Configuration
	static class CorsConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("http://localhost:5173")
					.allowedMethods("*")
					.allowCredentials(true);
		}
	}
}
