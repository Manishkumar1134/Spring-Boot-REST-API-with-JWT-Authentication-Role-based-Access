package com.springSecurityP1.SpringSecurity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PostUpdateRequest {

    @Schema(description = "Title of the post", example = "Updated Blog Title")
    private String title;

    @Schema(description = "Description of the post", example = "This is the updated description of the blog post.")
    private String description;
}
