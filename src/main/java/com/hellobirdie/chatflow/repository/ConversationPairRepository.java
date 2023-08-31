package com.hellobirdie.chatflow.repository;

import com.hellobirdie.chatflow.entity.ConversationPair;
import com.hellobirdie.chatflow.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationPairRepository extends JpaRepository<ConversationPair, Long> {

    Page<ConversationPair> findByMindmapIdOrderByCreatedTimeDesc(Long mindmapId, Pageable pageable);
}
