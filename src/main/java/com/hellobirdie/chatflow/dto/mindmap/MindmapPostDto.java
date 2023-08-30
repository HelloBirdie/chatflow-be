package com.hellobirdie.chatflow.dto.mindmap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MindmapPostDto {
    @NotBlank
    private String name;

    //    @NotBlank
    private long aiModelId;

    @NotBlank
    private String iconCode;
}
