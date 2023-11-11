package com.ssafy.realty.common.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private final String title = "Realty API";

    // http://localhost:8080/swagger-ui/index.html

    @Bean
    public Docket realtyApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentType())
                .apiInfo(apiInfo()).groupName("realty V1").select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.realty.realty.controller"))
                .paths(regex("/api/v1/*/.*"))
                .build()
                .useDefaultResponseMessages(false);
    }

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentType())
                .apiInfo(apiInfo()).groupName("user V1").select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.realty.user.adapter.in.web"))
                .paths(regex("/api/v1/*/.*"))
                .build()
                .useDefaultResponseMessages(false);
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentType() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
                .description("<h3>realty Api Document</h3>")
                .contact(new Contact("ksb", "https://ksb-dev.tistory.com", "qkfka9045@gmail.com"))
                .license("ksb license")
                .version("1.0").build();
    }
}
