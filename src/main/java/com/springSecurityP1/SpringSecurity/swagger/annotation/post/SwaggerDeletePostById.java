package com.springSecurityP1.SpringSecurity.swagger.annotation.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Delete a post by ID",
        description = "Deletes a specific post if the current user is the owner of the post."
)

@ApiResponse(
        responseCode = "200",
        description = "Post deleted successfully",
        content = @Content() // no response body for delete success
)
@ApiResponse(
        responseCode = "403",
        description = "User is not the owner of this post",
        content = @Content() // disables schema & example
)
@ApiResponse(
        responseCode = "404",
        description = "Post not found",
        content = @Content() // disables schema & example
)
public @interface SwaggerDeletePostById {

    @Parameter(description = "ID of the post to delete", example = "1")
    long id() default 0;

}
