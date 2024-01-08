package com.snowflake.team_8.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class ImageGenerationRequest implements Serializable {
    private String prompt;
    private int n;
    private String size;

    @Builder
    public ImageGenerationRequest(String prompt, int n, String size) {
        this.prompt = prompt;
        this.n = n;
        this.size = size;
    }
}
