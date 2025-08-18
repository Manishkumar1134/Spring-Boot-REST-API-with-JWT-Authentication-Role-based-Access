package com.springSecurityP1.SpringSecurity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    @Schema(
            description = "Full name of the user (cannot be empty, max 50 characters)",
            example = "John Updated"
    )
    private String name;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_]).*$",
            message = "Password must contain uppercase, lowercase, number, and special character with at least 8 characters long"
    )
    @Schema(
            description = """
            New password for the account.
            Rules:
            - 8 to 50 characters  
            - Must contain uppercase, lowercase, number, and special character
            """,
            example = "Updated@123"
    )
    private String password;
}
