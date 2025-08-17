package com.springSecurityP1.SpringSecurity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PostCreateRequest {

    @Schema(description = "Title of the post", example = "My first post")
    private String title;

    @Schema(description = "Description/content of the post", example = "This is a great post!")
    private String description;
}

