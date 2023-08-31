package com.hellobirdie.chatflow.service;


import com.hellobirdie.chatflow.dto.conversationPair.ConversationPairGetDto;
import com.hellobirdie.chatflow.entity.ConversationPair;
import com.hellobirdie.chatflow.entity.Message;
import com.hellobirdie.chatflow.mapper.ConversationPairMapper;
import com.hellobirdie.chatflow.repository.ConversationPairRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationPairService {

    private final ConversationPairRepository conversationPairRepository;
    private final ConversationPairMapper conversationPairMapper;

    public ConversationPairGetDto createConversationPair(Message userMessage, Message chatbotMessage) {

        ConversationPair conversationPair = ConversationPair.builder()
                .userMessage(userMessage)
                .aiMessage(chatbotMessage)
                .mindmap(userMessage.getMindmap())
                .build();

        conversationPair = conversationPairRepository.save(conversationPair);

        return conversationPairMapper.conversationPairToConversationPairGetDto(conversationPair);
    }
}
