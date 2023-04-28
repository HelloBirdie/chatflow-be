package com.hellobirdie.chatflow.data;

import com.hellobirdie.chatflow.data.converter.DateTimeDeserializer;
import com.hellobirdie.chatflow.data.converter.DateTimeSerializer;
import com.hellobirdie.chatflow.data.converter.JsonNodeConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "INT")
    private BigInteger id;

    @Column(name = "is_ai_message", columnDefinition = "BOOLEAN", nullable = false)
    private Boolean isAiMessage;

    @Column(name = "sender_id", columnDefinition = "INT", nullable = false)
    private BigInteger senderId;

    @Column(name = "text", columnDefinition = "VARCHAR(4028)")
    private String text;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN default false")
    private Boolean isDeleted;

    @Column(name = "created_time", columnDefinition = "DATETIME")
    @JsonDeserialize(using = DateTimeDeserializer.class)
    @JsonSerialize(using = DateTimeSerializer.class)
    private OffsetDateTime createdTime;

    @Column(name = "updated_time", columnDefinition = "DATETIME")
    @JsonDeserialize(using = DateTimeDeserializer.class)
    @JsonSerialize(using = DateTimeSerializer.class)
    private OffsetDateTime updatedTime;

}