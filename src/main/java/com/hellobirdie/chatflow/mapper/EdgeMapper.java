package com.hellobirdie.chatflow.mapper;

import com.hellobirdie.chatflow.dto.edge.EdgeGetDto;
import com.hellobirdie.chatflow.dto.edge.EdgePostDto;
import com.hellobirdie.chatflow.entity.Edge;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EdgeMapper {

        Edge edgePostDtoToEdge(EdgePostDto edgePostDto);

        EdgeGetDto edgeToEdgeGetDto(Edge edge);
}
