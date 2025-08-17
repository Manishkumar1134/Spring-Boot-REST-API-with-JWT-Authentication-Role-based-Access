package com.springSecurityP1.SpringSecurity.swagger.annotation.post;

import com.springSecurityP1.SpringSecurity.dto.PostCreateRequest;
import com.springSecurityP1.SpringSecurity.dto.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Create a new post",
        description = """
        Allows a user with ROLE_USER to create a post by providing a title and description.

        | Role  | Permissions                              |
        |-------|------------------------------------------|
        | USER  | POST_CREATE, POST_DELETE                 |
        """,
        requestBody = @RequestBody(
                description = "Post details with title and description",
                required = true,
                content = @Content(schema = @Schema(implementation = PostCreateRequest.class))
        )
)
@ApiResponse(
        responseCode = "200",
        description = "Post created successfully",
        content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = PostDto.class))
)
@ApiResponse(responseCode = "403", description = "User not authorized to create posts",content = @Content())
@ApiResponse(responseCode = "400", description = "Invalid request data",content = @Content())
public @interface SwaggerCreatePost {
}

