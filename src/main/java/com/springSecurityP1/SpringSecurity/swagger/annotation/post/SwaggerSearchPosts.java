package com.springSecurityP1.SpringSecurity.swagger.annotation.post;

import com.springSecurityP1.SpringSecurity.dto.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Search posts",
        description = "Search posts by a given keyword. Keyword can match against fields like title or description."
)
@ApiResponse(
        responseCode = "200",
        description = "Posts found successfully",
        content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = PostDto.class)))
)
@ApiResponse(responseCode = "404", description = "No posts found matching the keyword", content = @Content)
@ApiResponse(responseCode = "400", description = "Invalid search keyword", content = @Content)
public @interface SwaggerSearchPosts {
}
