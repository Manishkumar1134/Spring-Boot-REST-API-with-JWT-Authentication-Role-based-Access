package com.springSecurityP1.SpringSecurity.dto;

import com.springSecurityP1.SpringSecurity.entity.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    //@Schema in DTO → documents each field (name, email, password, etc.)
    //@Schema(implementation = SignUpDto.class) in Swagger annotation → tells Swagger what object to expect in the request body and shows an example payload
    @Schema(description = "Full name of the user", example = "John Doe")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Schema(description = "Email address of the user", example = "john_doe134@gmail.com")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_]).*$",
            message = "Password must contain uppercase, lowercase, number, and special character with at least 8 characters long"
    )
    @Schema(description = "Password for the account", example = "John@doe123")
    private String password;

    @Schema(description = "Roles assigned to the user", example = "[\"ADMIN\", \"USER\"]")
    private Set<Role> roles;
}
