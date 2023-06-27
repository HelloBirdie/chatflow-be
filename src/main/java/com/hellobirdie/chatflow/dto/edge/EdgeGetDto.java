package com.hellobirdie.chatflow.dto.edge;

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
public class EdgeGetDto {
    private Long edgeId;
    private Long sourceId;
    private Long targetId;
    private String edgeInfo;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;
}
