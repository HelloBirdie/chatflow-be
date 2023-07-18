package com.hellobirdie.chatflow.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "edge")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Edge{

    @Id
    @Column(name = "id")
    private Long Id;

    @Column(name = "source_id")
    private Long sourceId;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "edge_info")
    private String edgeInfo;

    @CreationTimestamp
    @Column(name = "create_time")
    private OffsetDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private OffsetDateTime updateTime;
}