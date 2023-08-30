package com.hellobirdie.chatflow.repository;

import com.hellobirdie.chatflow.entity.AiModel;
import com.hellobirdie.chatflow.entity.Mindmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiModelRepository extends JpaRepository<AiModel, Long> {

    AiModel getById(Long id);
}
