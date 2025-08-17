package com.springSecurityP1.SpringSecurity.swagger.annotation.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Logs out current logged in user",
        description = "Logs out the user by invalidating the refresh token from cookies.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
                @ApiResponse(responseCode = "200", description = "Logout successful",content = @Content()),
                @ApiResponse(responseCode = "401", description = "Not authenticated",content = @Content())
        }
)
public @interface SwaggerLogoutOperation {
}
