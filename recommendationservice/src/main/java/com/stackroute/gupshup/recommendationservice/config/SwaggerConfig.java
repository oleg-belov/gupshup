package com.stackroute.gupshup.recommendationservice.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/*----class for making swagger routes-------*/
	@Bean
	public Docket productApi() {
				return new Docket(DocumentationType.SWAGGER_2)
						.select()
						.apis(RequestHandlerSelectors.basePackage("com.stackroute.gupshup.recommendationservice.controller"))
						.paths(regex("/.*"))
						.build()
						.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(
				"Spring Boot USER REST API",
				"Spring Boot REST API for USER",
				"1.0",
				"Terms of service",
				new Contact("Stack Route", "https://stackroute.in/", "Ayushee"),
				"Apache License Version 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}
}

