package com.hellobirdie.chatflow.dto.edge;

import com.hellobirdie.chatflow.dto.userSetting.UserSettingSlimDto;

import java.time.OffsetDateTime;

public class EdgeGetDto {
    private long edge_id;

    private int source_id;

    private int target_id;

    private String edge_info;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;
}
