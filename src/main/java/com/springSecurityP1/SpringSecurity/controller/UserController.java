package com.springSecurityP1.SpringSecurity.controller;

import com.springSecurityP1.SpringSecurity.dto.UpdateUserRequest;
import com.springSecurityP1.SpringSecurity.dto.UserDto;
import com.springSecurityP1.SpringSecurity.dto.UserResponseDto;
import com.springSecurityP1.SpringSecurity.dto.UserUpdateResponse;
import com.springSecurityP1.SpringSecurity.entity.User;
import com.springSecurityP1.SpringSecurity.service.UserService;
import com.springSecurityP1.SpringSecurity.swagger.annotation.user.SwaggerGetAllUsers;
import com.springSecurityP1.SpringSecurity.swagger.annotation.user.SwaggerUpdateUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER_VIEW')")
    @SwaggerGetAllUsers
    public ResponseEntity<UserResponseDto> getAllUsers(
            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir) {

        UserResponseDto response = userService.getAllUsersWithPaginationAndSorting(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(response);
    }


    //Update name & password
    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id") //only the user themself can update their profile
    @SwaggerUpdateUser
    public ResponseEntity<UserUpdateResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest updateRequest
    ) {
        UserUpdateResponse updatedUser = userService.updateUser(id, updateRequest);
        return ResponseEntity.ok(updatedUser);
    }
}
