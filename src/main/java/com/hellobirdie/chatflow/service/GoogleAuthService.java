package com.hellobirdie.chatflow.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.dto.user.UserPostDto;
import com.hellobirdie.chatflow.entity.User;
import com.hellobirdie.chatflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${google.client.id}")
    private String googleClientId;

    public UserGetDto authenticateGoogleUser(String accessToken) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), new JacksonFactory())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(accessToken);
            if (idToken != null) {
                Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                String name = (String) payload.get("name");
                String picture = (String) payload.get("picture");

                Optional<User> existingUser = userRepository.findByEmail(email);
                if (existingUser.isPresent()) {
                    return convertToDto(existingUser.get());
                } else {
                    // Create new user
                    UserPostDto newUser = new UserPostDto(
                        name,           // username
                        email,          // email
                        passwordEncoder.encode(generateRandomPassword()), // password
                        picture         // avatar
                    );

                    User user = convertToEntity(newUser);
                    user = userRepository.save(user);
                    return convertToDto(user);
                }
            }
            throw new RuntimeException("Invalid ID token");
        } catch (Exception e) {
            throw new RuntimeException("Error authenticating with Google", e);
        }
    }

    private String generateRandomPassword() {
        return java.util.UUID.randomUUID().toString();
    }

    private User convertToEntity(UserPostDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAvatar(dto.getAvatar());
        return user;
    }

    private UserGetDto convertToDto(User user) {
        return UserGetDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .subscriptionLevel(0)  // 默认订阅级别
                .createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime())
                .build();
    }
} 