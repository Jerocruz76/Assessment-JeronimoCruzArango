package com.example.demo.infrastructure.open_api;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {
    @Value("${server.port}")
    public String serverPort;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public OpenAPI customOpenApi(){
        String serverUrl = String.format("http://localhost:%s%s", this.serverPort, this.contextPath);

        Server server = new Server().url(serverUrl).description("Cloud server");

        Info info = new Info().title("Riwi Spring Boot assessment").version("1.0.0").description("Rest API project to manage loads, pallets and stores");

        Components components = new Components().addSecuritySchemes("bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT").name("Authorization"));
        return new OpenAPI().info(info).servers(Collections.singletonList(server)).components(components);
    }
}
