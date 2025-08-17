package com.springSecurityP1.SpringSecurity.swagger.annotation.post;


import com.springSecurityP1.SpringSecurity.dto.PostDto;
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
        summary = "Get a post by ID",
        description = "Fetches a specific post if the current user is the owner of this post."
)
@ApiResponse(responseCode = "200", description = "Post retrieved successfully",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))
)
@ApiResponse(responseCode = "403", description = "User is not the owner of this post",
        content = @Content() // disables schema & example
         )
@ApiResponse(responseCode = "404", description = "Post not found",
        content = @Content() // disables schema & example
)
public @interface SwaggerGetPostById {

    @Parameter(description = "ID of the post to retrieve", example = "1")
    long id() default 0; // Optional default so it can be overridden if needed

}

