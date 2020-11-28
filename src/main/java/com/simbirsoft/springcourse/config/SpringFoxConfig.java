package com.simbirsoft.springcourse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
        return new ApiInfo("Employee database API",
                "Simple API as a tutorial",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact(
                        "Brynza Alexandr",
                        "https://github.com/BrynzaA/MySpringApp",
                        "abrynza73@gmail.com"),
                null,
                null,
                Collections.emptyList());
    }
}