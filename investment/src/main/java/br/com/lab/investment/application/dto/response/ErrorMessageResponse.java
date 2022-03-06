package br.com.lab.investment.application.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorMessageResponse {
    private final int statusCode;
    private final LocalDateTime timestamp;
    private final String message;
    private final String description;

    public ErrorMessageResponse(int statusCode, LocalDateTime timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
}
