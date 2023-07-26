package com.hellobirdie.chatflow.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "mindmap")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mindmap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ai_id", nullable = false)
    private AiModel aiModel;

    @CreationTimestamp
    private OffsetDateTime createTime;

    @UpdateTimestamp
    private OffsetDateTime updateTime;

    @OneToOne(mappedBy = "mindmap", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MindmapSetting mindmapSetting;
}
