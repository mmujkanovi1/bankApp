package com.example.bank.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI myOpenAPI() {
    OpenAPI openAPI = new OpenAPI()
        .components(new Components());

    Info info = new Info()
        .title("BankApp")
        .version("1.0")
        .description("REST API for Banking service.");

    return openAPI.info(info);
  }

}
