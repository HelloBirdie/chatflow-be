package com.hellobirdie.chatflow.service;

import com.hellobirdie.chatflow.auth.ChatflowUserDetail;
import com.hellobirdie.chatflow.dto.mindmap.MindmapGetDto;
import com.hellobirdie.chatflow.dto.mindmap.MindmapPostDto;
import com.hellobirdie.chatflow.dto.mindmapSetting.MindmapSettingSlimDto;
import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.entity.AiModel;
import com.hellobirdie.chatflow.entity.Mindmap;
import com.hellobirdie.chatflow.entity.MindmapSetting;
import com.hellobirdie.chatflow.entity.User;
import com.hellobirdie.chatflow.mapper.MindmapMapper;
import com.hellobirdie.chatflow.mapper.MindmapSettingMapper;
import com.hellobirdie.chatflow.repository.AiModelRepository;
import com.hellobirdie.chatflow.repository.MindmapRepository;
import com.hellobirdie.chatflow.repository.MindmapSettingRepository;
import com.hellobirdie.chatflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MindmapService {

    private final MindmapRepository mindmapRepository;
    private final MindmapMapper mindmapMapper;
    private final UserRepository userRepository;
    private final AiModelRepository aiModelRepository;
    private final MindmapSettingRepository mindmapSettingRepository;
    private final MindmapSettingMapper mindmapSettingMapper;

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

    @Transactional
    public MindmapGetDto createMindmap(MindmapPostDto mindmapPostDto) {
        UserGetDto user;
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((ChatflowUserDetail) userDetails).getId();

        User owner = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not exist. id=" + userId));
        AiModel aiModel = aiModelRepository.findById(mindmapPostDto.getAiModelId()).orElseThrow(() -> new IllegalArgumentException("AiModel not exist. id=" + mindmapPostDto.getAiModelId()));

        Mindmap mindmap = Mindmap.builder()
                .owner(owner)
                .aiModel(aiModel)
                .name(mindmapPostDto.getName())
                .iconCode(mindmapPostDto.getIconCode())
                .build();

        mindmap = mindmapRepository.save(mindmap);

        MindmapSetting mindmapSetting = MindmapSetting.builder()
                .mindmap(mindmap)
                .backgroundColor("default")
                .build();

        mindmapSetting = mindmapSettingRepository.save(mindmapSetting);

        MindmapSettingSlimDto mindmapSettingSlimDto = mindmapSettingMapper.mindmapSettingToMindmapSettingSlimDto(mindmapSetting);

        MindmapGetDto mindmapGetDto = mindmapMapper.mindmapToMindmapGetDto(mindmap);
        mindmapGetDto.setMindmapSettingSlimDto(mindmapSettingSlimDto);

        return mindmapGetDto;

    }

    @Transactional
    public void deleteMindmap(Long mindmapId) {

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((ChatflowUserDetail) userDetails).getId();

        // Check if user is owner of mindmap
        Mindmap mindmap = mindmapRepository.findById(mindmapId).orElseThrow(() -> new IllegalArgumentException("Mindmap not exist. id=" + mindmapId));

        if (mindmap.getOwner().getId() != userId) {
            throw new IllegalArgumentException("User is not owner of mindmap. userId=" + userId + ", mindmapId=" + mindmapId);
        } else {
            mindmapRepository.deleteById(mindmapId);
//            mindmapSettingRepository.deleteById(mindmapId); // no need to delete mindmapSetting because of cascade

            log.info("Mindmap deleted. mindmapId=" + mindmapId);
        }

    }
}