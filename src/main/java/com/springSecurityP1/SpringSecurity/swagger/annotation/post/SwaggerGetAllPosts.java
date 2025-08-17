package com.springSecurityP1.SpringSecurity.swagger.annotation.post;

import com.springSecurityP1.SpringSecurity.dto.PostResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Get all posts with pagination & sorting",
        description = "Fetches posts with support for pagination and sorting. " +
                "Requires `POST_VIEW` authority."
)
@ApiResponse(
        responseCode = "200",
        description = "Posts retrieved successfully",
        content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = PostResponseDto.class))
)
@ApiResponse(
        responseCode = "400",
        description = "Invalid request parameters (pageNo < 0, invalid sort field, etc.)",
        content = @Content()
)
@ApiResponse(
        responseCode = "403",
        description = "User not authorized to view posts",
        content = @Content()
)
@ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = @Content()
)
public @interface SwaggerGetAllPosts {
}
