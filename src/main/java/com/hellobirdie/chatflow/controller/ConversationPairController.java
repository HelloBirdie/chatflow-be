package com.hellobirdie.chatflow.controller;

import com.hellobirdie.chatflow.dto.conversationPair.ConversationPairGetDto;
import com.hellobirdie.chatflow.dto.message.MessagePostDto;
import com.hellobirdie.chatflow.service.ChatbotService;
import com.hellobirdie.chatflow.service.ConversationPairService;
import com.hellobirdie.chatflow.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/conversation-pairs")
@RequiredArgsConstructor
public class ConversationPairController {

    private final ConversationPairService conversationPairService;

    @GetMapping("/getAllConversationPairs")
    public ResponseEntity<Page<ConversationPairGetDto>> getAllConversationPairs(@RequestParam Long mindmapId,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdTime"));
        Page<ConversationPairGetDto> result = conversationPairService.findConversationPairsByMindmapId(mindmapId, pageable);

        return ResponseEntity.ok(result);
    }
}
