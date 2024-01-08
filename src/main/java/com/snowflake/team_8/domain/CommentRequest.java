package com.snowflake.team_8.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
//Front단에서 요청하는 DTO
public class CommentRequest implements Serializable {
    private String comment;

    public CommentRequest(String comment) {
        this.comment = comment;
    }
}
