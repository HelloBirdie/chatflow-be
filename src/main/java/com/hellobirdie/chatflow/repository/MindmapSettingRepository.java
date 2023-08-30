package com.hellobirdie.chatflow.repository;

import com.hellobirdie.chatflow.entity.MindmapSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MindmapSettingRepository extends JpaRepository<MindmapSetting, Long> {

    
}
