package com.hellobirdie.chatflow.repository;

import com.hellobirdie.chatflow.entity.Mindmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MindmapRepository extends JpaRepository<Mindmap, Long> {

    List<Mindmap> findByOwnerId(Long ownerId);
}
