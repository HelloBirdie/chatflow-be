package com.hellobirdie.chatflow.repository;



import com.hellobirdie.chatflow.data.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.List;
@Repository
@Entity
public interface MessageRepository extends JpaRepository<Message, BigInteger> {

    @Query(value = "SELECT * from message Where id = :id", nativeQuery = true)
    Message findMById(@Param("id") BigInteger id);
}
