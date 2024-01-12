package com.snowflake.team_8.domain;


import lombok.Data;


@Data
public class SavingImageRequest {
    private String imageUrl;

    private String imageDescription;
}
