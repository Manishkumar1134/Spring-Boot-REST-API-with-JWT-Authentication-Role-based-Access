package com.springSecurityP1.SpringSecurity.controller;

import com.springSecurityP1.SpringSecurity.dto.Login;
import com.springSecurityP1.SpringSecurity.dto.LoginResponseDto;
import com.springSecurityP1.SpringSecurity.dto.SignUpDto;
import com.springSecurityP1.SpringSecurity.dto.UserDto;
import com.springSecurityP1.SpringSecurity.service.AuthService;
import com.springSecurityP1.SpringSecurity.service.SessionService;
import com.springSecurityP1.SpringSecurity.service.UserService;
import com.springSecurityP1.SpringSecurity.swagger.annotation.auth.SwaggerLoginOperation;
import com.springSecurityP1.SpringSecurity.swagger.annotation.auth.SwaggerLogoutOperation;
import com.springSecurityP1.SpringSecurity.swagger.annotation.auth.SwaggerRefreshTokenOperation;
import com.springSecurityP1.SpringSecurity.swagger.annotation.auth.SwaggerSignUpOperation;
import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
//This API won't have any security requirements. Therefore, we need to override the default
// security requirement configuration with @SecurityRequirements()
@SecurityRequirements()
@Tag(name = "Authentication", description = "Endpoints for user sign-up, login, token refresh, and logout")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final SessionService sessionService;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    @SwaggerSignUpOperation
    public UserDto createNewUser(@Valid @RequestBody SignUpDto userDto) {
        return userService.signUp(userDto);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @SwaggerLoginOperation
    public LoginResponseDto login(@RequestBody Login login, HttpServletResponse response) {
        LoginResponseDto token = authService.login(login);
        Cookie cookie = new Cookie("refreshToken", token.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return token;
    }

    @DeleteMapping("/logout")
    @SwaggerLogoutOperation
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new JwtException("Refresh Token not found inside the cookies."));
        sessionService.logout(refreshToken);
        return ResponseEntity.ok("You have been logged out from this device.");
    }

    @PostMapping("/refresh")
    @SwaggerRefreshTokenOperation
    public LoginResponseDto refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new JwtException("Refresh Token not found inside the cookies."));
        return authService.refresh(refreshToken);
    }

}
