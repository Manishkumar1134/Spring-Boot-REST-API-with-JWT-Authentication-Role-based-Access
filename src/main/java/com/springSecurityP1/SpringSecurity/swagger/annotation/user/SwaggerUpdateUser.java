package com.springSecurityP1.SpringSecurity.swagger.annotation.user;

import com.springSecurityP1.SpringSecurity.dto.UpdateUserRequest;
import com.springSecurityP1.SpringSecurity.dto.UserDto;
import com.springSecurityP1.SpringSecurity.dto.UserUpdateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Update user profile",
        description = "Allows a user to update their own profile details. " +
                "The authenticated user can only update their own profile (id in path must match logged-in user)."
)
@Parameters({
        @Parameter(
                name = "id",
                description = "User ID of the profile to update (must match the authenticated user)",
                example = "1"
        )
})
@RequestBody(
        required = true,
        description = "New profile data (name, password)",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UpdateUserRequest.class)
        )
)
@ApiResponse(
        responseCode = "200",
        description = "User updated successfully",
        content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserUpdateResponse.class)) // use DTO, not entity
)
@ApiResponse(
        responseCode = "400",
        description = "Invalid input data (e.g., empty name or password)",
        content = @Content()
)
@ApiResponse(
        responseCode = "403",
        description = "Forbidden – user not allowed to update another user’s profile",
        content = @Content()
)
@ApiResponse(
        responseCode = "404",
        description = "User not found with given id",
        content = @Content()
)
@ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = @Content()
)
public @interface SwaggerUpdateUser {
}
