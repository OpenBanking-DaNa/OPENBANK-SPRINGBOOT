package com.bank.ng.exception;

/* 예외 처리용 어드바이스 추가
* 요청 처리에 있어서 Controller 단에서 발생하는 각 예외 상황별 예외 처리용
* @RestControllerAdvice = @ControllerAdvice + @ResponseBody*/

import com.bank.ng.exception.dto.ApiExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {

    /* AuthService에서 비밀번호 불일치 시 발생하는 예외처리 */
    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ApiExceptionDTO> exceptionHandler(LoginFailedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    /* TokenProvider에서 토큰 유효성 검사용 메소드 정의 시 사용
    * 유효성 검사 메소드는 JwtFilter에서 토큰 유효성 검사 시 발생하는 예외 상황 처리 */

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiExceptionDTO> exceptionHandler(TokenException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiExceptionDTO(HttpStatus.UNAUTHORIZED, e.getMessage()));

    }


}
