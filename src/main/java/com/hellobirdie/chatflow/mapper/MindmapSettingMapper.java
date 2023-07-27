package com.hellobirdie.chatflow.mapper;


import com.hellobirdie.chatflow.dto.mindmapSetting.MindmapSettingSlimDto;
import com.hellobirdie.chatflow.entity.MindmapSetting;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MindmapSettingMapper {

    MindmapSettingSlimDto mindmapSettingToMindmapSettingSlimDto(MindmapSetting mindmapSetting);
}
