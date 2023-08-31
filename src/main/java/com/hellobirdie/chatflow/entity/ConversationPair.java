package com.hellobirdie.chatflow.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "node_count")
    private int nodeCount;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @CreationTimestamp
    private OffsetDateTime createdTime;

    @UpdateTimestamp
    private OffsetDateTime updatedTime;

}
