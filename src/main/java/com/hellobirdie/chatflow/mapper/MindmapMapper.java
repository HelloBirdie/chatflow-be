package com.hellobirdie.chatflow.mapper;

import com.hellobirdie.chatflow.dto.mindmap.MindmapGetDto;
import com.hellobirdie.chatflow.entity.Mindmap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MindmapMapper {

    @Mapping(source = "aiModel.model", target = "aiModel")
    MindmapGetDto mindmapToMindmapGetDto(Mindmap mindmap);
}
