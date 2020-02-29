package com.rest.webservices.restfulwebservices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wordnik.swagger.models.Contact;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome Api Title", "Api Documentation", "1.0",
			"urn:tos", "Contact Email", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	private static final Set<String> PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/JSON","application/XML"));
	
	
	
	

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(PRODUCES_AND_CONSUMES).consumes(PRODUCES_AND_CONSUMES);
	}
}
