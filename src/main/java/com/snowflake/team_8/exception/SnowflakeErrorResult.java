package com.snowflake.team_8.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SnowflakeErrorResult implements ErrorResult {

    NON_OWNER(HttpStatus.BAD_REQUEST, "Requestor is not owner"),
    REMINI_NOT_FOUND(HttpStatus.NOT_FOUND, "Failed to find the Remini"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
