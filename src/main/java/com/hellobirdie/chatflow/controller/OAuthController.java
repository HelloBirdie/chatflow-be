package com.hellobirdie.chatflow.controller;


import com.hellobirdie.chatflow.dto.auth.GoogleAuthDto;
import com.hellobirdie.chatflow.dto.auth.GoogleUserProfileDto;
import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.dto.user.UserPostDto;
import com.hellobirdie.chatflow.jwt.JwtConfig;
import com.hellobirdie.chatflow.jwt.JwtService;
import com.hellobirdie.chatflow.service.GoogleService;
import com.hellobirdie.chatflow.service.UserService;
import com.hellobirdie.chatflow.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final UserService userService;

    private final GoogleService googleService;

    private final JwtService jwtService;

    private static final String BEARER = "Bearer ";

    private final SecretKey secretKey;

    private final JwtConfig jwtConfig;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login/google")
    public ResponseEntity<?> GoogleAuth(@Valid @RequestBody GoogleAuthDto googleAuthDto) {

        GoogleUserProfileDto googleUserProfileDto = googleService.getUserProfile(googleAuthDto.getAccessToken());

        String email;
        Long userId;
        UserGetDto userGetDto;

        // Check if the user exists in the database
        if (userService.isEmailExist(googleUserProfileDto.getEmail())) {
            // If the user exists, return the user profile
            userGetDto = userService.getUserByEmail(googleUserProfileDto.getEmail());
        } else {
            // If the user does not exist, create a new user
            UserPostDto userPostDto = UserPostDto.builder().username(googleUserProfileDto.getName()).email(googleUserProfileDto.getEmail()).avatar(googleUserProfileDto.getPicture()).password(passwordEncoder.encode(StringUtils.generateRandomPassword(16))).build();

            userGetDto = userService.createUser(userPostDto);

        }

        email = userGetDto.getEmail();
        userId = userGetDto.getId();
        String jwtToken = jwtService.createJwt(email, userId, secretKey, java.sql.Date.valueOf(LocalDate.now().plusDays(7)));
        return ResponseEntity.ok().header(jwtConfig.getAuthorization(), BEARER + jwtToken).body(userGetDto);
    }
}
