package com.hellobirdie.chatflow.service;

import com.hellobirdie.chatflow.dto.edge.EdgeDelDto;
import com.hellobirdie.chatflow.dto.edge.EdgeGetDto;
import com.hellobirdie.chatflow.dto.edge.EdgePostDto;
import com.hellobirdie.chatflow.dto.node.NodeGetDto;
import com.hellobirdie.chatflow.dto.node.NodePostDto;
import com.hellobirdie.chatflow.dto.user.UserGetDto;
import com.hellobirdie.chatflow.dto.user.UserPostDto;
import com.hellobirdie.chatflow.entity.Edge;
import com.hellobirdie.chatflow.entity.Node;
import com.hellobirdie.chatflow.entity.User;
import com.hellobirdie.chatflow.mapper.EdgeMapper;
import com.hellobirdie.chatflow.mapper.NodeMapper;
import com.hellobirdie.chatflow.repository.EdgeRepository;
import com.hellobirdie.chatflow.repository.NodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NodeService {
    private final NodeRepository nodeRepository;
    private final NodeMapper nodeMapper;
    private final UserService userService;
    private final MindmapService mindmapService;
    private final ConversationPairService conversationPairService;


    //create edge
    public NodeGetDto createNode(NodePostDto nodePostDto) {
        // get user id
        Long userId = userService.getUserIdByToken();
        // get mindmap id
        Long mindmapId = nodePostDto.getMindmapId();
        // get conversation pair id
        Long conversationPairId = nodePostDto.getConversationPairId();

        // check if user owns the mindmap
        if (mindmapService.findById(mindmapId).getOwner().getId() != userId) {
            throw new IllegalArgumentException("You are not owner of this mindmap");
        }

        // check if conversation pair belongs to the mindmap
        if (conversationPairService.findConversationPairById(conversationPairId).getMindmapId() != mindmapId) {
            throw new IllegalArgumentException("Conversation pair does not belong to this mindmap");
        }

        Node node = nodeMapper.nodePostDtoToNode(nodePostDto);
        log.info("Saving new node from mindmap {} to database", nodePostDto.getMindmapId());
        return nodeMapper.nodeToNodeGetDto(nodeRepository.save(node));
    }

//    public EdgeGetDto deleteEdge(long id) {
//        Edge edge = edgeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Non-exist id=" + id));
//        edgeRepository.delete(edge);
//        return edgeMapper.edgeToEdgeGetDto(edge);
//    }
//
//    public EdgeGetDto updateEdge(EdgePostDto edgePostDto) {
//        Edge edge = edgeMapper.edgePostDtoToEdge(edgePostDto);
//        //print edge
//        return edgeMapper.edgeToEdgeGetDto(edgeRepository.save(edge));
//    }
//
//    public EdgeGetDto getEdge(Long id) {
//        Edge edge = edgeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Non-exist id=" + id));
//        return edgeMapper.edgeToEdgeGetDto(edge);
//    }
}
