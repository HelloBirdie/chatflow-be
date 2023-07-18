package com.hellobirdie.chatflow.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.hellobirdie.chatflow.dto.auth.GoogleUserProfileDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class GoogleServiceTest {

    @InjectMocks
    private GoogleService googleService;

    @BeforeEach
    void setMockOutput() {
        ReflectionTestUtils.setField(googleService, "webClient", mock(WebClient.class));

    }

    @Test
    public void testGetUserProfile() throws Exception {


        GoogleUserProfileDto payload = googleService.getUserProfile("ya29.a0AbVbY6N9vWbD4YMBF6RH6_TBji4pzjVIAoobbgbzB07ag1F_CjaQxj6IMddDszTVqN3N5lJaAv60hLFWiYojrE3pqfwsrza73bCX3ZKS0iCQwmkIN6mRQUg3iHBkDagoKtwK11u9-EPRMQFbVWU8_auP7MP1KwaCgYKAV4SARESFQFWKvPlIq5uJXuFNgvB3JKFiMw-uQ0165");

        System.out.println(payload.getEmail());

    }
}
