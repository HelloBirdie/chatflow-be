package com.hellobirdie.chatflow.service;

import com.hellobirdie.chatflow.auth.ChatflowUserDetail;
import com.hellobirdie.chatflow.dto.mindmap.MindmapGetDto;
import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.entity.Mindmap;
import com.hellobirdie.chatflow.mapper.MindmapMapper;
import com.hellobirdie.chatflow.repository.MindmapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MindmapService {

    private final MindmapRepository mindmapRepository;
    private final MindmapMapper mindmapMapper;

    public List<MindmapGetDto> getAllMindmaps() {
        UserGetDto user;
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((ChatflowUserDetail) userDetails).getId();

        List<Mindmap> mindmaps = mindmapRepository.findByOwnerId(userId);

        List<MindmapGetDto> mindmapGetDtoList = mindmaps.stream()
                .map(mindmapMapper::mindmapToMindmapGetDto)
                .collect(Collectors.toList());

        return mindmapGetDtoList;
    }

}