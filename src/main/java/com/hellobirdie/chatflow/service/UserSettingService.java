package com.hellobirdie.chatflow.service;


import com.hellobirdie.chatflow.entity.User;
import com.hellobirdie.chatflow.entity.UserSetting;
import com.hellobirdie.chatflow.repository.UserSettingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSettingService {

    private final UserSettingRepository userSettingRepository;

    private final String DEFAULT_LANGUAGE = "en";

    public UserSetting createUserSettingFromUser(User user) {
        log.info("Creating user setting for user with id: {}", user.getId());

        UserSetting userSetting = UserSetting.builder()
                .user(user)
                .language(DEFAULT_LANGUAGE)
                .build();
        return userSettingRepository.save(userSetting);
    }
}
