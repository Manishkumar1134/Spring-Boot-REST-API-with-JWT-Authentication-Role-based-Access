package com.springSecurityP1.SpringSecurity.config;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
//
//@OpenAPIDefinition(
//        info = @Info(
//                title = "Spring Security with Role Based Access",
//                version = "1.0",
//                description = "Spring Boot REST API with JWT Authentication & Authorization with Access Token & Refresh Token"
//        )
//)
//@SecurityScheme(
//        name = "bearerAuth",
//        type = SecuritySchemeType.HTTP,
//        scheme = "bearer",
//        bearerFormat = "JWT"
//)
//public class SwaggerConfig {
//}


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                final String securitySchemeName = "bearerAuth";

                return new OpenAPI()
                        .info(new Info()
                                .contact(new Contact()
                                        .name("Manish Kumar")
                                        .email("mauryamanish247@gmail.com"))
                                .title("Spring Security with Role Based Access")
                                .version("1.0")
                                .description("Spring Boot REST API with JWT Authentication & Authorization with Access Token & Refresh Token"))
                        .addSecurityItem(new SecurityRequirement()
                                .addList(securitySchemeName))
                        .components(new io.swagger.v3.oas.models.Components()
                                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(In.HEADER)));
        }
}

