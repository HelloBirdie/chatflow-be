package com.hellobirdie.chatflow.controller;

import com.hellobirdie.chatflow.dto.node.NodeGetDto;
import com.hellobirdie.chatflow.dto.node.NodePostDto;
import com.hellobirdie.chatflow.service.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("nodes")
@RequiredArgsConstructor
public class NodeController {

    private final NodeService nodeService;

    @PostMapping
    public ResponseEntity<NodeGetDto> createNode(@Valid @RequestBody NodePostDto nodePostDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nodeService.createNode(nodePostDto));
    }
}
