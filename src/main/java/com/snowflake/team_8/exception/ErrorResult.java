package com.snowflake.team_8.exception;

import org.springframework.http.HttpStatus;

public interface ErrorResult {

    HttpStatus getHttpStatus();

    String getMessage();

    String name();
}
