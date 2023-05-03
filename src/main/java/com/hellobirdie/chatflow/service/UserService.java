package com.hellobirdie.chatflow.service;


import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.dto.user.UserPostDto;
import com.hellobirdie.chatflow.dto.user.UserPwdDto;
import com.hellobirdie.chatflow.entity.User;
import com.hellobirdie.chatflow.mapper.UserMapper;
import com.hellobirdie.chatflow.repository.UserRepository;
import com.hellobirdie.chatflow.dto.ErrorDto.IncorrectPasswordException;
import com.hellobirdie.chatflow.dto.ErrorDto.PasswordNotConfirmedException;
import com.hellobirdie.chatflow.dto.ErrorDto;

import ch.qos.logback.core.joran.conditional.ElseAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;



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

    public UserGetDto updatePwdById(Long id, UserPwdDto userPwdDto) {
        List<Long> idList = Collections.singletonList(id);
        User user = userRepository.findAllById(idList).get(0);
        if (!user.getPassword().equals(userPwdDto.getOldPassword())) {
            log.warn("Old password is incorrect");
            throw new ErrorDto("Error message", List.of("Error details")).new IncorrectPasswordException();
        }
        //TODO: User must enter the new password twice to confirm
        else if (!userPwdDto.getConfirmPassword().equals(userPwdDto.getNewPassword())) {
            log.warn("New password is not confirmed");
            throw new ErrorDto("Error message", List.of("Error details")).new PasswordNotConfirmedException();
        }
        else {
            log.info("Your password has been updated");
            user.setPassword(userPwdDto.getNewPassword());
            return userMapper.userToUserGetDto(userRepository.save(user));
        }
    }

}
