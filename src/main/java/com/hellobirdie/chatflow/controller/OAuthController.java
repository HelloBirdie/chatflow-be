package com.hellobirdie.chatflow.controller;


import com.hellobirdie.chatflow.dto.auth.GoogleAuthDto;
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
    private GoogleService googleService;

    private JwtService jwtService;

    private static final String BEARER = "Bearer ";

    private final SecretKey secretKey;

    @PostMapping("/login/google")
    public ResponseEntity<?> GoogleAuth(@Valid @RequestBody GoogleAuthDto googleAuthDto) {
        log.info("google login {}", googleAuthDto);
        UserGetDto userGetDto = googleService.getUserProfile(googleAuthDto.getAccessToken());

        String email = userGetDto.getEmail();
        Long userId = userGetDto.getId();

        String jwtToken = jwtService.createJwt(email, userId, secretKey, java.sql.Date.valueOf(LocalDate.now().plusDays(7)));

        return ResponseEntity.ok().header("Authorization", BEARER + jwtToken).body(userGetDto);

    }
}
