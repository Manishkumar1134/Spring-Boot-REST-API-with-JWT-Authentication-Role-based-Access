package com.springSecurityP1.SpringSecurity.swagger.annotation.post;

import com.springSecurityP1.SpringSecurity.dto.PostDto;
import com.springSecurityP1.SpringSecurity.dto.PostUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Update a post",
        description = "Updates an existing post by ID. Only the owner of the post or an ADMIN can perform this action. " +
                "Supports updating **only title and description**."
)
@ApiResponse(
        responseCode = "200",
        description = "Post updated successfully",
        content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = PostDto.class))
)
@ApiResponse(responseCode = "400", description = "Invalid request payload or parameters", content = @Content())
@ApiResponse(responseCode = "403", description = "User not authorized to update this post", content = @Content())
@ApiResponse(responseCode = "404", description = "Post not found with given ID", content = @Content())
@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content())
public @interface SwaggerUpdatePost {
}
