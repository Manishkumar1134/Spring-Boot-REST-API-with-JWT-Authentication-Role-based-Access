package com.springSecurityP1.SpringSecurity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostUpdateRequest {


    @Size(min = 3, max = 100, message = "Title should not be empty, min 3 characters max 100 characters")
    @Schema(
            description = "Title of the post (should not be empty, min 3 characters max 100 characters)",
            example = "Updated Blog Title"
    )
    private String title;


    @Size(min = 10, max = 1000, message = "Description should not be empty, min 10 characters max 1000 characters")
    @Schema(
            description = "Description of the post (should not be empty, min 10 characters max 1000 characters)",
            example = "This is the updated description of the blog post."
    )
    private String description;
}
