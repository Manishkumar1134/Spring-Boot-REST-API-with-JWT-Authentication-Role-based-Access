package com.springSecurityP1.SpringSecurity.swagger.annotation.auth;

import com.springSecurityP1.SpringSecurity.dto.LoginResponseDto;
import com.springSecurityP1.SpringSecurity.dto.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Refresh access token",
        description = "Generates a new access token using the refresh token from cookies.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
                @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
                @ApiResponse(responseCode = "401", description = "Invalid or missing refresh token",content = @Content())
        }
)
public @interface SwaggerRefreshTokenOperation {
}

