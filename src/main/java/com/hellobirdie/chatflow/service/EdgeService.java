package com.hellobirdie.chatflow.service;

import com.hellobirdie.chatflow.dto.edge.EdgeGetDto;
import com.hellobirdie.chatflow.dto.edge.EdgePostDto;
import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.dto.user.UserPostDto;
import com.hellobirdie.chatflow.entity.Edge;
import com.hellobirdie.chatflow.entity.User;
import com.hellobirdie.chatflow.mapper.EdgeMapper;
import com.hellobirdie.chatflow.repository.EdgeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EdgeService {
    private final EdgeRepository edgeRepository;
    private final EdgeMapper edgeMapper;

    //create edge
    public EdgeGetDto createEdge(EdgePostDto edgePostDto) {
        Edge edge = edgeMapper.edgePostDtoToEdge(edgePostDto);
        //print edge
        log.info("Edge111111: {}", edge);

        return edgeMapper.edgeToEdgeGetDto(edgeRepository.save(edge));
    }

}