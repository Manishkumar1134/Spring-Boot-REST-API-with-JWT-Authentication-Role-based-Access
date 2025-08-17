package com.springSecurityP1.SpringSecurity.swagger.annotation.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.lang.annotation.*;

@Target(ElementType.METHOD) //restricts it to methods (good for controller endpoints).
@Retention(RetentionPolicy.RUNTIME) //the annotation is read at runtime, placement is unrestricted, not in Javadocs.
@Documented //makes it show up in generated API docs.

@Operation(
        summary = "User login",
        description = "Authenticates the user and returns access & refresh tokens. Refresh token is stored in an HTTP-only cookie.",
        requestBody = @RequestBody(
                description = "Login credentials",
                required = true,
                content = @Content(
                        schema = @Schema(implementation = com.springSecurityP1.SpringSecurity.dto.Login.class),
                        examples = @ExampleObject(
                                value = "{ \"email\": \"john_doe@gmail.com\", \"password\": \"P@ssw0rd123\" }"
                        )
                )
        ),
        responses = {
                @ApiResponse(responseCode = "200", description = "Login successful",content = @Content()),
                @ApiResponse(responseCode = "401", description = "Invalid credentials",content = @Content())
        }
)
public @interface SwaggerLoginOperation {
}

