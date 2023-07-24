package com.hellobirdie.chatflow.service;

import com.hellobirdie.chatflow.dto.auth.GoogleUserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
//@ConfigurationProperties(prefix = "google")
public class GoogleService {
    private UserService userService;

    private final WebClient webClient;

    @Value("${google.client-id}")
    private String clientId;


    public GoogleUserProfileDto getUserProfile(String accessToken) {

        log.info("get user profile {} from google", accessToken);
        return webClient
                .get()
                .uri("https://www.googleapis.com/oauth2/v2/userinfo")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(GoogleUserProfileDto.class).block();
    }


}
