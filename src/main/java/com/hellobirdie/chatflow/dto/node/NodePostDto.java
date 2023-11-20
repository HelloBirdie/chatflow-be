package com.hellobirdie.chatflow.dto.node;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter // Generates getters for all fields
@Setter // Generates setters for all fields
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields as arguments
@ToString // Generates a toString method for the class
public class NodePostDto implements Serializable {

    private long mindmapId; // or Long, depending on the identifier's type
    private long conversationPairId; // same as above, could also be Long
    private String userMessage;
    private String aiMessage;
    private int x;
    private int y;
}