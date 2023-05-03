package com.hellobirdie.chatflow.dto.user;

import com.hellobirdie.chatflow.dto.userSetting.UserSettingSlimDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserGetDto {

    private long id;
    private String username;
    private String email;
    private String avatar;
    private int subscriptionLevel;

    private UserSettingSlimDto userSetting;

    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;

}
