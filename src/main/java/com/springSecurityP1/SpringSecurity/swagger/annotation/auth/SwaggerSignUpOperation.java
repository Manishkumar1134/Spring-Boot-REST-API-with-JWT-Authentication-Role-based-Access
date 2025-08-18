package com.springSecurityP1.SpringSecurity.swagger.annotation.auth;

import com.springSecurityP1.SpringSecurity.dto.SignUpDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Register a new user",
        description = """
        Creates a new user account in the system.

        | Role  | Permissions                              |
        |-------|------------------------------------------|
        | USER  | POST_CREATE, POST_DELETE                 |
        | ADMIN | POST_VIEW, POST_UPDATE, USER_VIEW        |
        """,
        requestBody = @RequestBody(
                description = "Sign-up details",
                required = true,
                content = @Content(
                        schema = @Schema(implementation = SignUpDto.class),
                        examples = @ExampleObject(
                                value = "{\n" +
                                        "  \"name\": \"John_doe\",\n" +
                                        "  \"email\": \"john_doe123@gmail.com\",\n" +
                                        "  \"password\": \"John@doe123\",\n" +
                                        "  \"roles\": [\"ADMIN\",\"USER\"]\n" +
                                        "}"
                        )
                )
        ),
        responses = {
                @ApiResponse(responseCode = "200", description = "User created successfully",content = @Content()),
                @ApiResponse(responseCode = "400", description = "Invalid input",content = @Content())
        }
)
public @interface SwaggerSignUpOperation {
}

