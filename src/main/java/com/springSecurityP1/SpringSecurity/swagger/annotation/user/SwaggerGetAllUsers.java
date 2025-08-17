package com.springSecurityP1.SpringSecurity.swagger.annotation.user;

import com.springSecurityP1.SpringSecurity.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Get all users with pagination & sorting",
        description = "Fetches users with support for pagination and sorting. " +
                "Requires `USER_VIEW` authority."
)
@Parameters({
        @Parameter(
                name = "pageNo",
                description = "Page number (zero-based index)",
                example = "0"
        ),
        @Parameter(
                name = "pageSize",
                description = "Number of records per page (max 100)",
                example = "3"
        ),
        @Parameter(
                name = "sortBy",
                description = "Field name to sort by (id, name, email)",
                example = "id"
        ),
        @Parameter(
                name = "sortDir",
                description = "Sort direction: asc or desc",
                example = "asc"
        )
})
@ApiResponse(
        responseCode = "200",
        description = "Users retrieved successfully",
        content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserResponseDto.class))
)
@ApiResponse(
        responseCode = "400",
        description = "Invalid request parameters (pageNo < 0, invalid sort field, etc.)",
        content = @Content()
)
@ApiResponse(
        responseCode = "403",
        description = "User not authorized to view users",
        content = @Content()
)
@ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = @Content()
)
public @interface SwaggerGetAllUsers {
}

