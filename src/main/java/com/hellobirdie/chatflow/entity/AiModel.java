package com.hellobirdie.chatflow.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "ai")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AiModel {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String model;

    @CreationTimestamp
    private OffsetDateTime createTime;

    @UpdateTimestamp
    private OffsetDateTime updateTime;
}
