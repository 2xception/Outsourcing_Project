package com.sparta.outsourcing.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("api")
            .pathsToMatch("/api/**")
            .build();
    }

    @Bean
    public OpenAPI springOutsourcingOpenAPI() {
        String title = "outsourcing Application Swagger";
        String description = "outsourcing Application 팀 프로젝트의 Swagger 문서입니다.";

        Info info = new Info().title(title).description(description).version("1.0.0");

        return new OpenAPI().info(info);
    }
}
