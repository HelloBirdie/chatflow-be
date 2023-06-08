package com.hellobirdie.chatflow.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

//is_ai_message: boolean
//sender_id: integer
//text: string

@Getter
@Setter
@AllArgsConstructor
public class MessagePostDto {

    @NotNull(message = "is_ai_message must not be null")
    private boolean is_ai_message;

    @NotNull(message = "sender_id must not be null")
    private int sender_id;

    @NotBlank(message = "text must not be blank")
    @Size(max = 4028, message = "text can not be more than 4028 characters.")
    private String text;
}
