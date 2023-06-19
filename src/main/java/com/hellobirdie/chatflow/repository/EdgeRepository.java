package com.hellobirdie.chatflow.repository;

import com.hellobirdie.chatflow.entity.Edge;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface EdgeRepository extends  JpaRepository<Edge, Long>{

}
