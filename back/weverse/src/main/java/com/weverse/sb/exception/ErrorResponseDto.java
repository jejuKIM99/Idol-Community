package com.weverse.sb.exception;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponseDto {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    private List<FieldError> errors;

    public static ErrorResponseDto of(HttpStatus status, String message, String path) {
        return ErrorResponseDto.builder()
                .status(status.value())
                .message(message)
                .timestamp(LocalDateTime.now())
                .path(path)
                .errors(Collections.emptyList())
                .build();
    }

    public static ErrorResponseDto of(HttpStatus status, String message, String path, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors().stream()
                .map(err -> new FieldError(
                        err.getField(),
                        String.valueOf(err.getRejectedValue()),
                        err.getDefaultMessage()))
                .toList();

        return ErrorResponseDto.builder()
                .status(status.value())
                .message(message)
                .timestamp(LocalDateTime.now())
                .path(path)
                .errors(fieldErrors)
                .build();
    }

    @Getter
    @AllArgsConstructor
    public static class FieldError {
        private String field;
        private String rejectedValue;
        private String reason;
    }
}


