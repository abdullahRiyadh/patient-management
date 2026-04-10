package com.abdullah.patient_management.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * @author Abdullah Riyadh
 * @date 4/10/26
 */

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Patient Management System API",
                version = "1.0.0",
                description = "REST API for managing patients, doctors, and appointments",
                contact = @Contact(
                        name = "Abdullah Riyadh",
                        email = "abdullahriyadhcse@gmail.com"
                )
        ),
        servers = {
                @Server(url = "/", description = "Current server")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT token. Enter: Bearer <your_token>",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
