package com.hellobirdie.chatflow.controller;

import com.hellobirdie.chatflow.dto.mindmap.MindmapGetDto;
import com.hellobirdie.chatflow.dto.mindmap.MindmapPostDto;
import com.hellobirdie.chatflow.service.MindmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/add")
    public MindmapGetDto createMindmap(@Valid @RequestBody MindmapPostDto mindmapPostDto) {
        MindmapGetDto mindmapGetDto = mindmapService.createMindmap(mindmapPostDto);

        return mindmapGetDto;
    }

    @DeleteMapping("/delete/{mindmapId}")
    public ResponseEntity<Void> createMindmap(@PathVariable Long mindmapId) {
        mindmapService.deleteMindmap(mindmapId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{mindmapId}")
    public MindmapGetDto updateMindmap(@PathVariable Long mindmapId, @Valid @RequestBody MindmapPostDto mindmapPostDto) {
        MindmapGetDto mindmapGetDto = mindmapService.updateMindmap(mindmapId, mindmapPostDto);

        return mindmapGetDto;
    }
}
