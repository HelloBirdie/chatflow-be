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
public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long edgeId;

    @Column(nullable = false)
    private Long sourceId;

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = true)
    private String edgeInfo;

    @CreationTimestamp
    private OffsetDateTime create_Time;

    @UpdateTimestamp
    private OffsetDateTime update_Time;
}
