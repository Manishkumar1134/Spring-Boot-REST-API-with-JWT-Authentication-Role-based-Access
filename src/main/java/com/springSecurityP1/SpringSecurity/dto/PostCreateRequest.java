package com.springSecurityP1.SpringSecurity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostCreateRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 25, message = "Title should not be empty,min 3 characters max 25 characters")
    @Schema(
            description = "Title of the post (should not be empty,min 3 characters max 25 characters)",
            example = "My First Post"
    )
    private String title;

    @NotBlank(message = "Description cannot be empty")
    @Size(min= 10,max = 1000, message = "Description should not be empty,min 10 characters max 1000 characters")
    @Schema(
            description = "Description/content of the post (should not be empty,min 10 characters max 1000 characters)",
            example = "This is a great post! It explains the basics of Spring Boot and JWT authentication."
    )
    private String description;
}
