package br.com.feliperbdantas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot 2025 REST API's do 0 à AWS e GCP c Java e Docker")
                        .description("Spring Boot 2025 REST API's do 0 à AWS e GCP c Java e Docker")
                        .version("v1")
                        .contact(new Contact()
                                .name("Felipe Dantas")
                                .url("http://github.com/FelipeRBDantas")
                                .email("feliperbdantas@outlook.com")
                        )
                        .termsOfService("https://termsOfService.com.br")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://license.com.br")
                        )
                );
    }
}
