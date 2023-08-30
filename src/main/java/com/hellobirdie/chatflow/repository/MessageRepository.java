package com.hellobirdie.chatflow.repository;

import com.hellobirdie.chatflow.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
