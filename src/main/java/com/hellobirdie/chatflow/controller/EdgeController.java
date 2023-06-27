package com.hellobirdie.chatflow.controller;

import com.hellobirdie.chatflow.dto.edge.EdgeGetDto;
import com.hellobirdie.chatflow.dto.edge.EdgePostDto;
import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.dto.user.UserLoginDto;
import com.hellobirdie.chatflow.dto.user.UserPostDto;
import com.hellobirdie.chatflow.dto.user.UserPwdDto;
import com.hellobirdie.chatflow.service.EdgeService;
import com.hellobirdie.chatflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("edges")
@RequiredArgsConstructor
public class EdgeController {
    private final EdgeService edgeService;
    //create edge
    @PostMapping("/create")
    public ResponseEntity<EdgeGetDto> createEdge(@Valid @RequestBody EdgePostDto edgePostDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(edgeService.createEdge(edgePostDto));
    }
}
