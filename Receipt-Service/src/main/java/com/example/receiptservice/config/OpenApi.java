package com.example.receiptservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Receipt Service",
                version = "1.0",
                description = "This is a receipt service"
        )
)
@Configuration
public class OpenApi {
}
