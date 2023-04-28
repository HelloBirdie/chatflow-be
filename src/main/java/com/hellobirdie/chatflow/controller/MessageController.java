package com.hellobirdie.chatflow.controller;

import com.hellobirdie.chatflow.data.Message;
import com.hellobirdie.chatflow.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/lxz")
public class MessageController{

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/get")
    public Message get(@RequestParam("id")int id){
        return messageRepository.findMById(BigInteger.valueOf(id));
    }

    @PostMapping("/save")
    public Message save(@RequestBody Message message){
        return messageRepository.save(message);
    }

}
