package com.household.purchases.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI householdPurchasesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Household Purchases API")
                        .description("API для управління продуктами, меню та покупками")
                        .version("v1.0.0")
                );
    }
}
