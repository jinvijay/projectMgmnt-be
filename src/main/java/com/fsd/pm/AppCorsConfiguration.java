package com.fsd.pm;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.models.HttpMethod;

@Configuration
public class AppCorsConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedOrigins(CorsConfiguration.ALL)
				.allowedHeaders(CorsConfiguration.ALL).allowedMethods(HttpMethod.GET.toString(),
						HttpMethod.PUT.toString(), HttpMethod.POST.toString(), HttpMethod.DELETE.toString())
				.allowCredentials(true);
	}

}
