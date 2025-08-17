package com.springSecurityP1.SpringSecurity.dto;

import com.springSecurityP1.SpringSecurity.entity.enums.Permissions;
import com.springSecurityP1.SpringSecurity.entity.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Data
public class SignUpDto {
    private Long id;
    @Schema(description = "Full name of the user", example = "John_doe")
    private String name;

    @Schema(description = "Email address of the user", example = "john_doe134@gmail.com")
    private String email;

    @Schema(description = "Password for the account", example = "john_doe121")
    private String password;

    @Schema(description = "Roles assigned to the user", example = "[\"ADMIN\", \"USER\"]")
    private Set<Role> roles;
//    private Set<Permissions> permissions;
}
