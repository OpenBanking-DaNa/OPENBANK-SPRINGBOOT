package com.bank.ng.exception.dto;

/* 예외 발생 시에 보낼 데이터를 담을 DTO 객체 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ApiExceptionDTO {

    private int status;     // 코드
    private String message;     // 에러 메세지

    public ApiExceptionDTO() {
    }

    public ApiExceptionDTO(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
