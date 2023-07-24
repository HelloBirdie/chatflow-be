package com.hellobirdie.chatflow.service;


import com.hellobirdie.chatflow.auth.ChatflowUserDetail;
import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.dto.user.UserLoginDto;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserGetDto createUser(UserPostDto userPostDto) {


        String encodedPwd = passwordEncoder.encode(userPostDto.getPassword());
        User user = userMapper.userPostDtoToUser(userPostDto);
        user.setPassword(encodedPwd);

        log.info("Saving new user {} to database", user.getEmail());

        // TODO: add duplicate user error handling
        return userMapper.userToUserGetDto(userRepository.save(user));

        // TODO: add user setting
    }

    public List<UserGetDto> getSortedUserListByID(Boolean isAscending) {
        List<User> userList = userRepository.findAll();
        List<UserGetDto> userGetDtoList = new ArrayList<>();
        for (User user : userList) {
            userGetDtoList.add(userMapper.userToUserGetDto(user));
        }
        System.out.println(isAscending);
        if (isAscending == true)
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
        } else if (!userPwdDto.getConfirmPassword().equals(userPwdDto.getNewPassword())) {
            log.warn("New password is not confirmed");
            throw new ErrorDto("Error message", List.of("Error details")).new PasswordNotConfirmedException();
        } else {
            //TODO: encode the password
            log.info("Your password has been updated");
            user.setPassword(userPwdDto.getNewPassword());
            return userMapper.userToUserGetDto(userRepository.save(user));
        }
    }

    public UserGetDto loginByEmail(UserLoginDto userLoginDto) {
        Optional<User> userList = userRepository.findByEmail(userLoginDto.getEmail());
        if (userList.isEmpty()) {
            log.error("User with email {} not found", userLoginDto.getEmail());
            throw new ErrorDto("Error message", List.of("Error details")).new UserNotFoundException();
        }

        User user = userList.get();
        if (user.getPassword().equals(userLoginDto.getPassword())) {
            log.info("id: " + user.getId() + ", name: " + user.getUsername() + " successfully logins");
            return userMapper.userToUserGetDto(user);
        } else {
            log.warn("password is incorrect");
            throw new ErrorDto("Error message", List.of("Error details")).new IncorrectPasswordException();
        }
    }

    /*Check whether email exist*/

    public boolean isEmailExist(String email) {
        Optional<User> userList = userRepository.findByEmail(email);
        if (userList.isEmpty()) {
            return false;
        }
        return true;
    }

    public UserGetDto getUserByEmail(String email) {
        Optional<User> userList = userRepository.findByEmail(email);
        if (userList.isEmpty()) {
            log.error("User with email {} not found", email);
            throw new ErrorDto("Error message", List.of("Error details")).new UserNotFoundException();
        }
        User user = userList.get();
        return userMapper.userToUserGetDto(user);
    }

    public UserGetDto getUserById(Long id) {
        Optional<User> userList = userRepository.findById(id);
        if (userList.isEmpty()) {
            log.error("User with id {} not found", id);
            throw new ErrorDto("Error message", List.of("Error details")).new UserNotFoundException();
        }
        User user = userList.get();
        return userMapper.userToUserGetDto(user);
    }

    public UserGetDto getUserInfoByToken() {
        UserGetDto user;
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((ChatflowUserDetail) userDetails).getId();
        user = getUserById(userId);
        return user;
    }

}
