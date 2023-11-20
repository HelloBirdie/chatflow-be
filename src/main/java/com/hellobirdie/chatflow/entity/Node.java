package com.hellobirdie.chatflow.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "node")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mindmap_id", nullable = false)
    private Mindmap mindmap;

    @ManyToOne(optional = false)
    @JoinColumn(name = "conversation_pair_id", nullable = false)
    private ConversationPair conversationPair;

    @Column(name = "user_message", nullable = false)
    private String userMessage;

    @Column(name = "ai_message", nullable = false)
    private String aiMessage;

    @Column(name = "note")
    private String note;

    @Column(name = "color")
    private String color;

    @Column(name = "update_time", nullable = false)
    private OffsetDateTime updateTime;

    @Column(name = "create_time", nullable = false, updatable = false)
    private OffsetDateTime createTime;
    
    @Column(name = "x", nullable = false)
    private Long x;

    @Column(name = "y", nullable = false)
    private Long y;
}
