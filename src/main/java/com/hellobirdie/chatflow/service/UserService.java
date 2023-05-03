package com.hellobirdie.chatflow.service;


import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.dto.user.UserPostDto;
import com.hellobirdie.chatflow.entity.User;
import com.hellobirdie.chatflow.mapper.UserMapper;
import com.hellobirdie.chatflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserGetDto createUser(UserPostDto userPostDto) {
        User user = userMapper.userPostDtoToUser(userPostDto);

        log.info("Saving new user {} to database", user.getEmail());
        return userMapper.userToUserGetDto(userRepository.save(user));

        // TODO: add user setting
    }
}
