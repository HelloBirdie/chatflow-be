package com.hellobirdie.chatflow.service;

import com.hellobirdie.chatflow.dto.user.UserGetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleService {
    private UserService userService;

    public UserGetDto getUserProfile(String accessToken) {
        log.info("get user profile {} from google", accessToken);


    }
}
