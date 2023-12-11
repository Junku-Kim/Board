package com.jk.board.exception;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ErrorResponse {

	private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status; // 에러 코드 상태 ex) 403, 500
    private final String error; // HttpStatus에 만들어진 예외 ex) NOT_FOUND
    private final String code; // ErrorCode에서 만든 예외
    private final String message;

    public ErrorResponse(final ErrorCode errorCode) {
        this.status = errorCode.getStatus().value();
        this.error = errorCode.getStatus().name();
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }
}
