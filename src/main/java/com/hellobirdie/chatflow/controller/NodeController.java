package com.hellobirdie.chatflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nodes")
@RequiredArgsConstructor
public class NodeController {

//    @PostMapping("/create")
//    public ResponseEntity<NodeGetDto> createNode(@Valid @RequestBody NodePostDto nodePostDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(nodeService.createNode(nodePostDto));
//    }
}
