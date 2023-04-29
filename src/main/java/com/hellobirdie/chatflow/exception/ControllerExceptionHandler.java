package com.hellobirdie.chatflow.exception;

import com.hellobirdie.chatflow.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleUnexpectedExceptions(Exception e) {
        log.error("There is an unexpected exception occurred", e);
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), List.of(e.getMessage()));
    }
}
