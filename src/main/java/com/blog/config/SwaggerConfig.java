package com.blog.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	public static final String AUTHORIZATION_HEADER ="Authorization";

	private ApiKey apiKeys() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
	private List<SecurityContext> securityContext(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}
	
	private List<SecurityReference> sf(){
		
		AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
		
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
	}
	
	@Bean
	public Docket api() {
		System.out.println("inside swaggerConfig class");
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(securityContext())
				.securitySchemes(Arrays.asList(apiKeys()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo getInfo() {

		return new ApiInfo(
				"Blogging Application APIs", 
				"This project is developed to help user to create their own blog post and mange it", 
				"1.0", 
				"Terms of service", 
				new Contact("Avishek","https://avishekverma.com","avishek.verma02@gmail.com"), 
				"License of APIs", 
				"API license URL ",
				Collections.emptyList()
				);
	}
}
