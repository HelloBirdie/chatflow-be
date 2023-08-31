package com.hellobirdie.chatflow.service;

import com.hellobirdie.chatflow.dto.message.MessagePostDto;
import com.hellobirdie.chatflow.entity.Message;
import com.hellobirdie.chatflow.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatbotService {

    private final MessageMapper messageMapper;

    public Message getChatbotResponse(MessagePostDto messagePostDto) {

        String text = messagePostDto.getText();
        Long mindmapId = messagePostDto.getMindmapId();
        Long aiModelId = messagePostDto.getAiModelId();

        //TODO: send the message to chatgpt


        String response = "Hello, I am chatbot";

        MessagePostDto aiMessagePostDto = MessagePostDto.builder()
                .isAiMessage(true)
                .mindmapId(mindmapId)
                .text(response)
                .aiModelId(aiModelId)
                .build();

        Message aiMessage = messageMapper.messagePostDtoToMessage(aiMessagePostDto);
        aiMessage.setSenderId(aiModelId);

        return aiMessage;
    }
}
