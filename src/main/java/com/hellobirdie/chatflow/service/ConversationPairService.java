package com.hellobirdie.chatflow.service;


import com.hellobirdie.chatflow.auth.ChatflowUserDetail;
import com.hellobirdie.chatflow.dto.conversationPair.ConversationPairGetDto;
import com.hellobirdie.chatflow.entity.ConversationPair;
import com.hellobirdie.chatflow.entity.Message;
import com.hellobirdie.chatflow.entity.Mindmap;
import com.hellobirdie.chatflow.mapper.ConversationPairMapper;
import com.hellobirdie.chatflow.repository.ConversationPairRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationPairService {

    private final ConversationPairRepository conversationPairRepository;
    private final ConversationPairMapper conversationPairMapper;
    private final MindmapService mindmapService;

    public ConversationPairGetDto createConversationPair(Message userMessage, Message chatbotMessage) {

        ConversationPair conversationPair = ConversationPair.builder()
                .userMessage(userMessage)
                .aiMessage(chatbotMessage)
                .mindmap(userMessage.getMindmap())
                .build();

        conversationPair = conversationPairRepository.save(conversationPair);


        return conversationPairMapper.conversationPairToConversationPairGetDto(conversationPair);
    }

    public Page<ConversationPairGetDto> findConversationPairsByMindmapId(Long mindmapId, Pageable pageable) {

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((ChatflowUserDetail) userDetails).getId();

        Mindmap mindmap = mindmapService.findById(mindmapId);

        if (mindmap.getOwner().getId() != userId) {
            throw new IllegalArgumentException("You are not owner of this mindmap");
        }

        Page<ConversationPair> pairs = conversationPairRepository.findByMindmapIdOrderByCreatedTimeDesc(mindmapId, pageable);

        return pairs.map(conversationPairMapper::conversationPairToConversationPairGetDto);
    }
}
