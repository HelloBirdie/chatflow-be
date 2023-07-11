package com.hellobirdie.chatflow.service;

import com.hellobirdie.chatflow.dto.edge.EdgeDelDto;
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
        edge.setId(1L);
        return edgeMapper.edgeToEdgeGetDto(edgeRepository.save(edge));
    }

    public EdgeGetDto deleteEdge(long id) {
        Edge edge = edgeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Non-exist id=" + id));
        edgeRepository.delete(edge);
        return edgeMapper.edgeToEdgeGetDto(edge);
    }

    public EdgeGetDto updateEdge(EdgePostDto edgePostDto) {
        Edge edge = edgeMapper.edgePostDtoToEdge(edgePostDto);
        //print edge
        return edgeMapper.edgeToEdgeGetDto(edgeRepository.save(edge));
    }

    public EdgeGetDto getEdge(Long id) {
        Edge edge = edgeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Non-exist id=" + id));
        return edgeMapper.edgeToEdgeGetDto(edge);
    }
}