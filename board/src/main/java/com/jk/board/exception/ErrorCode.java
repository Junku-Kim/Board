package com.jk.board.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
/*
 * 일반적으로 @AllArgsConstructor는 일반 클래스에서 자동 생성된 생성자가
 * 의도하지 않은 부작용을 초래할 수 있고, 클래스의 상태 파악을 더 어렵게 할 수 있어
 * 직접 생성자를 만들고 @Builder를 사용 편이 더 좋지만 Enum 클래스에서는 괜찮다.
 */
@AllArgsConstructor
public enum ErrorCode {

	/*
     * 400 BAD_REQUEST: 잘못된 요청
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    /*
     * 401 UNAUTHORIZED: 권한 없음
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),

    /*
     * 402 PAYMENT_REQUIRED: 결제 필요
     */
    PAYMENT_REQUIRED(HttpStatus.PAYMENT_REQUIRED, "결제가 필요합니다."),

    /*
     * 403 FORBIDDEN: 접근 금지
     */
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근이 금지되었습니다."),

    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),

    /*
     * 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),

    /*
     * 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),

    /*
     * 503 SERVICE_UNAVAILABLE: 서비스 이용 불가
     */
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "서비스 이용이 불가능합니다."),

    ;

    private final HttpStatus status;
    private final String message;
}
