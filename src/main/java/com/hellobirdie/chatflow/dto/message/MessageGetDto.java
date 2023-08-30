package com.hellobirdie.chatflow.dto.message;

import com.hellobirdie.chatflow.dto.userSetting.UserSettingSlimDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

//id: integer
//is_ai_message: boolean
//sender_id: integer
//text: string
//update_time: date
//create_time: date
//is_deleted: boolean

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MessageGetDto {

    private long id;
    private boolean isAiMessage;
    private int senderId;
    private String text;
    private OffsetDateTime createdTime;
    private OffsetDateTime updatedTime;
}