package com.hellobirdie.chatflow.dto.edge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class EdgePostDto {
    @NotNull
    private long source_id;

    @NotNull
    private long target_id;

    @Size(max = 255, message = "Edge info can not be more than 255 characters.")
    private String edge_info;
}
