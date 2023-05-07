package com.hellobirdie.chatflow.controller;

import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.dto.user.UserLoginDto;
import com.hellobirdie.chatflow.dto.user.UserPostDto;
import com.hellobirdie.chatflow.dto.user.UserPwdDto;
import com.hellobirdie.chatflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserGetDto> createUser(@Valid @RequestBody UserPostDto userPostDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userPostDto));
    }

    //TODO: Get all users profile. How to use spring boot to get user ID?

    @GetMapping("/sortById")
    public ResponseEntity<List<UserGetDto>> getSortedUserListById(@RequestParam(value = "isAscending", defaultValue = "true") Boolean isAscending) {
        System.out.println(isAscending);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getSortedUserListByID(isAscending));
    }

    //TODO: Update the user's password based on authorization
    @PostMapping("/updatePwdById")
    public ResponseEntity<UserGetDto> updatePwdById(@RequestParam(value = "id") Long id,@Valid @RequestBody UserPwdDto userPwdDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updatePwdById(id, userPwdDto));
    }

    @PostMapping("/loginByEmail")
    public ResponseEntity<UserGetDto> loginByEmail(@RequestParam(value = "email") String email,@Valid @RequestBody UserLoginDto userLoginDto) {
        System.out.println("email: " + email + ", password: " + userLoginDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(userService.loginById(email, userLoginDto));
    }


}
