package com.jk.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
/*
 * final을 이용해 ErrorCode를 통한 객체 생성만을 하므로
 * @AllArgsConstructor을 사용해도 괜찮다.
 */
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

}
