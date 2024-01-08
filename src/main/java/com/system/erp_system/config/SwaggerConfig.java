package com.system.erp_system.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Demo1 application",
                version = "1.0",
                contact = @Contact(
                        name = " admin", email = "niyozovshoxrux277@gmail.com"
                ),
                description = "just watch"
        ),
        servers = {
                @Server(url = "http://localhost:${server.port}", description = "Local development")
        }
)
@SecurityScheme(
        name = SwaggerConfig.BEARER,
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
    public static final String BEARER = "Bearer Authentication";
}
