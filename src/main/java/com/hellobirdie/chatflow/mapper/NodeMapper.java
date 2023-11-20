package com.hellobirdie.chatflow.mapper;

import com.hellobirdie.chatflow.dto.node.NodeGetDto;
import com.hellobirdie.chatflow.dto.node.NodePostDto;
import com.hellobirdie.chatflow.entity.Node;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NodeMapper {

    Node nodePostDtoToNode(NodePostDto nodePostDto);

    NodeGetDto nodeToNodeGetDto(Node node);
}
