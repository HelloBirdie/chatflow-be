package com.hellobirdie.chatflow.controller;

import com.hellobirdie.chatflow.dto.mindmap.MindmapGetDto;
import com.hellobirdie.chatflow.service.MindmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("mindmap")
@RequiredArgsConstructor
public class MindmapController {

    private final MindmapService mindmapService;

    @GetMapping("/getAllMindmaps")
    public List<MindmapGetDto> getAllMindmaps() {
        List<MindmapGetDto> mindmaps = mindmapService.getAllMindmaps();

        return mindmaps;
    }
}
