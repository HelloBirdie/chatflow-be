package com.hellobirdie.chatflow.mapper;

import com.hellobirdie.chatflow.dto.conversationPair.ConversationPairGetDto;
import com.hellobirdie.chatflow.dto.message.MessageGetDto;
import com.hellobirdie.chatflow.dto.message.MessagePostDto;
import com.hellobirdie.chatflow.entity.ConversationPair;
import com.hellobirdie.chatflow.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConversationPairMapper {

    @Mapping(source = "mindmap.id", target = "mindmapId")
    ConversationPairGetDto conversationPairToConversationPairGetDto(ConversationPair conversationPair);
}
