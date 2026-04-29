package com.nyw.cateringsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("餐饮管理系统 API")
                        .description("Catering System RESTful API 文档")
                        .version("1.0.0")
                        .contact(new Contact().name("宁有为")));
    }
}
