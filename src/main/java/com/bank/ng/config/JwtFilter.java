package com.bank.ng.config;

import com.bank.ng.jwt.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* OncePerRequestFilter : 사용자의 요청에 한번 동작하는 필터
* -> 동일한 요청 안에서 한번단 Filtering 할 수 있게 해주는 것
*
* 왜 씀?
* 인증, 인가를 거치고 특정 URL로 포워딩하면 요청이 들어오니 인증, 인가 filter 다시 실행함
* OncePerRequestFilter를 이용하면 인증, 인가를 한번만 거치고, 다음 로직 수행 가능함
* 매번 하지 않아도 됨
* */

public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    // 사용자가 request header에 Authorization 속성으로 token을 던진다
    public static final String BEARER_PREFIX = "Bearer";
    // 사용자가 던지는 토큰 값만 파싱하기 위한 접두사 저장용 변수(접두사는 Bearer라는 표준으로 정의됨)

    private final TokenProvider tokenProvider;
    public JwtFilter(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = resolveToken(request); // accessToken
        /* 추출한 토큰의 유효성 검사 후 인증을 위해 Authentication 객체를 SecurityContextHolder에 담는다*/
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response); // 다음 filterchain 진행

    }

    /* Request Header에서 토큰 정보 꺼내기(위에서 선언한 두 개의 static 변수 2개 여기서 사용) */
    private String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)){
            return bearerToken.substring(7); // 사용자가 보낸 토큰 값 추출 띄어쓰기 포함 (토큰 생성 패턴 : Bearer accessToken)
        }
        return null;
    }
}

