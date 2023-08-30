package com.hellobirdie.chatflow.controller;

import com.hellobirdie.chatflow.dto.message.MessageGetDto;
import com.hellobirdie.chatflow.dto.message.MessagePostDto;
import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.dto.user.UserPostDto;
import com.hellobirdie.chatflow.dto.user.UserPwdDto;
import com.hellobirdie.chatflow.service.MessageService;
import com.hellobirdie.chatflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public MessageGetDto createMessage(@RequestBody @Valid MessagePostDto messagePostDto) {
        MessageGetDto messageGetDto = messageService.createMessage(messagePostDto);
        return messageGetDto;
    }
}
