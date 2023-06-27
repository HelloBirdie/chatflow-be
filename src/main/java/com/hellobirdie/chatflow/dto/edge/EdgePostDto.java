package com.hellobirdie.chatflow.dto.edge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class EdgePostDto {

    private Long sourceId;

    private Long targetId;

    @NotBlank(message = "edgeInfo cannot be blank")
    private String edgeInfo;
}
