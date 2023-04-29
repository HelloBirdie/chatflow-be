package com.hellobirdie.chatflow.dto.userSetting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSettingSlimDto {
    private Long id;
    private String language;
}
