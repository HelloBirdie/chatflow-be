package com.hellobirdie.chatflow.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "conversation_pair")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversationPair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mindmap_id", nullable = false)
    private Mindmap mindmap;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_messsage_id", nullable = false)
    private Message userMessage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ai_message_id", nullable = false)
    private Message aiMessage;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column(name = "created_time", nullable = false, updatable = false)
    private OffsetDateTime createdTime;

    @Column(name = "updated_time", nullable = false)
    private OffsetDateTime updatedTime;

}
