package com.hellobirdie.chatflow.dto.mindmap;

import com.hellobirdie.chatflow.dto.mindmapSetting.MindmapSettingSlimDto;
import com.hellobirdie.chatflow.entity.MindmapSetting;
import lombok.*;

import java.time.OffsetDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MindmapGetDto {

    private long id;

    private String name;

    private MindmapSettingSlimDto mindmapSettingSlimDto;

    private String aiModel;

    private String iconCode;

    private OffsetDateTime createTime;

    private OffsetDateTime updateTime;
}
