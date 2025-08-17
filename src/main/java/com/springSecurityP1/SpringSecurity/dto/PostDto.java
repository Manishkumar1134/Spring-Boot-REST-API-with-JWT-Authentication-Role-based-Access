package com.springSecurityP1.SpringSecurity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PostDto {
        private Long id;
        private String title;
        private String description;
        private UserDto author;

}
