package com.hellobirdie.chatflow.service;


import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.dto.user.UserPostDto;
import com.hellobirdie.chatflow.entity.User;
import com.hellobirdie.chatflow.mapper.UserMapper;
import com.hellobirdie.chatflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserGetDto> getSortedUserListByID(Boolean isAscending) {
        List<User> userList = userRepository.findAll();
        List<UserGetDto> userGetDtoList = new ArrayList<>();
        for (User user: userList){
            userGetDtoList.add(userMapper.userToUserGetDto(user));
        }
        System.out.println(isAscending);
        if(isAscending == true)
            return userGetDtoList.stream()
                    .sorted(Comparator.comparing(UserGetDto::getId))
                    .collect(Collectors.toList());
        else
            return userGetDtoList.stream()
                .sorted(Comparator.comparing(UserGetDto::getId).reversed())
                .collect(Collectors.toList());
    }

}
