package com.eventmanagement.eventmanagement.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/public.*"), regex("/api.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Event Management System")
                .description("Github repo for the back end source code of Event Management project made during the internship problem in Deutsche Bank. The Technologies used were Spring Boot, Hibernate Framework, JPA Framework, and OAuth2. ")
                .termsOfServiceUrl("localhost:8080")
                .contact(new Contact("Mufaddal Naya", "localhost:8080","mufaddal.naya@gmail.com")).license("JavaInUse License")
                .version("1.0").build();
    }

}