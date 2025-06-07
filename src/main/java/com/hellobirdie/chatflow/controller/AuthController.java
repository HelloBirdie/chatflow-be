package com.hellobirdie.chatflow.controller;

import com.hellobirdie.chatflow.dto.auth.GoogleLoginRequest;
import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GoogleAuthService googleAuthService;

    @PostMapping("/login/google")
    public ResponseEntity<UserGetDto> googleLogin(@RequestBody GoogleLoginRequest request) {
        UserGetDto user = googleAuthService.authenticateGoogleUser(request.getAccessToken());
        return ResponseEntity.ok(user);
    }
} 