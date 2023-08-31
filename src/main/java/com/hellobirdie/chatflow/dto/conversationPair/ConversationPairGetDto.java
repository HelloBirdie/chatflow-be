package com.hellobirdie.chatflow.dto.conversationPair;

import com.hellobirdie.chatflow.dto.message.MessageGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ConversationPairGetDto {

    private long id;
    private long mindmapId;
    private MessageGetDto userMessage;
    private MessageGetDto aiMessage;
    private OffsetDateTime createdTime;
    private OffsetDateTime updatedTime;
    private int nodeCount;
}
