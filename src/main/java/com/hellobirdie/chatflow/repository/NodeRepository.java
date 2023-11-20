package com.hellobirdie.chatflow.repository;

import com.hellobirdie.chatflow.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Long> {
}
