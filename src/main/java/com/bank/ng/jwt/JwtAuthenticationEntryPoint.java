package com.bank.ng.jwt;

/* SecurityConfig의 권한별 요청에 대한 예외처리용으로 생성
* 필요한 권한 접근 시 403 상태코드 발생*/

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        /* 필요한 권한이 없을 때 */
        response.sendError(HttpServletResponse.SC_FORBIDDEN);


    }
}
