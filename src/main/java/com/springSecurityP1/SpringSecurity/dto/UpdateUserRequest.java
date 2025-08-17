package com.springSecurityP1.SpringSecurity.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String password;
}