package com.hellobirdie.chatflow.mapper;

import com.hellobirdie.chatflow.dto.message.MessageGetDto;
import com.hellobirdie.chatflow.dto.message.MessagePostDto;
import com.hellobirdie.chatflow.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    Message messagePostDtoToMessage(MessagePostDto messagePostDto);

    MessageGetDto messageToMessageGetDto(Message message);
}
