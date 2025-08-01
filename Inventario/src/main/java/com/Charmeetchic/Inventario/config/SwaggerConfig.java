package com.Charmeetchic.Inventario.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI inventarioOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Inventario - Charmeetchic")
                        .version("1.0")
                        .description("Gestión de inventario con alertas por stock mínimo."));
    }
}
