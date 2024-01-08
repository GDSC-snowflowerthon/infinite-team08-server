package com.snowflake.team_8.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SnowflakeException extends RuntimeException implements CustomException {

    private final ErrorResult errorResult;
}
