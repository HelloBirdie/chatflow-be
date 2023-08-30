package com.hellobirdie.chatflow.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "message")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mindmap_id", nullable = false)
    private Mindmap mindmap;

    @Column(name = "is_ai_message", nullable = false)
    private boolean isAiMessage;

    @Column(name = "sender_id", nullable = false)
    private Long senderId;

    @Column(name = "text", nullable = false)
    private String text;

    @CreationTimestamp
    private OffsetDateTime createdTime;

    @UpdateTimestamp
    private OffsetDateTime updatedTime;


}
