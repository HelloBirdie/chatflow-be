package com.hellobirdie.chatflow.controller;


import com.hellobirdie.chatflow.dto.auth.GoogleAuthDto;
import com.hellobirdie.chatflow.dto.auth.GoogleUserProfileDto;
import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.jwt.JwtService;
import com.hellobirdie.chatflow.service.GoogleService;
import com.hellobirdie.chatflow.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Slf4j
public class OAuthController {

    private UserService userService;

    private final GoogleService googleService;

    private JwtService jwtService;

    private static final String BEARER = "Bearer ";

    private final SecretKey secretKey;

    @PostMapping("/login/google")
    public ResponseEntity<?> GoogleAuth(@Valid @RequestBody GoogleAuthDto googleAuthDto) {

        GoogleUserProfileDto googleUserProfileDto = googleService.getUserProfile(googleAuthDto.getAccessToken());

        System.out.println(googleUserProfileDto);

        return null;

    }
}
