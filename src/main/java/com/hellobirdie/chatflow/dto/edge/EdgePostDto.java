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
    @NotBlank
    private int source_id;

    @NotBlank(message = "Email must not be blank.")
    private int target_id;

    @Size(max = 255, message = "Edge info can not be more than 255 characters.")
    private String edge_info;
}
