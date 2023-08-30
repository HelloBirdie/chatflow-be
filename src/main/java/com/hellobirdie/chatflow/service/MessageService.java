package com.hellobirdie.chatflow.service;

import com.hellobirdie.chatflow.auth.ChatflowUserDetail;
import com.hellobirdie.chatflow.dto.message.MessageGetDto;
import com.hellobirdie.chatflow.dto.message.MessagePostDto;
import com.hellobirdie.chatflow.entity.Message;
import com.hellobirdie.chatflow.entity.Mindmap;
import com.hellobirdie.chatflow.mapper.MessageMapper;
import com.hellobirdie.chatflow.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;
    private final MindmapService mindmapService;
    private final MessageMapper messageMapper;

    @Transactional
    public MessageGetDto createMessage(MessagePostDto messagePostDto) {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((ChatflowUserDetail) userDetails).getId();

        Mindmap mindmap = mindmapService.findById(messagePostDto.getMindmapId());

        if (mindmap.getOwner().getId() != userId) {
            throw new IllegalArgumentException("You are not owner of this mindmap");
        }

        Message message = messageMapper.messagePostDtoToMessage(messagePostDto);
        message.setMindmap(mindmap);
        message.setSenderId(userId);


        message = messageRepository.save(message);

        return messageMapper.messageToMessageGetDto(message);
    }
}
