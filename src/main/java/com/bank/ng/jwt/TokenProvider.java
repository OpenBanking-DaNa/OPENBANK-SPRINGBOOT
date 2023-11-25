package com.bank.ng.jwt;

import com.bank.ng.exception.TokenException;
import com.bank.ng.member.dto.TokenDTO;
import com.bank.ng.member.entity.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


/* 토큰 생성, 토큰 인증(Authentication 객체 반환), 토큰 유효성 검사 진행할 클래스 */
@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer"; // Bearer 토큰 사용 시 앞에 붙이는 prefix문자열
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; // 30분(ms 단위)
    // long 타입 -> 밀리세컨드 => 1000분의 1초

    private final Key key;

    private final UserDetailsService userDetailsService;

    // 외부 파일에서 값 들고오기 (값을 base64로 인코딩해서 사용하면 됨)
    public TokenProvider(@Value("${jwt.secret}") String secretKey, UserDetailsService userDetailsService) {

        byte[] keyBytest = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytest);
        this.userDetailsService = userDetailsService;
    }

    /* 1. 토큰 생성 메소드 */

    public TokenDTO generateTokenDTO(Member member) {
        log.info("[TokenProvider] generateTokenDTO Start =============== ");

        // 1-1. 회원 아이디를 "sub"이라는 클레임으로 토큰 추가
        Claims claims = Jwts.claims().setSubject(member.getMemberId());

        long now = System.currentTimeMillis(); // 현재 시간을 밀리세컨드 단위로 가져옴

        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME); // java.util.Date로 import
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(accessTokenExpiresIn)    // 토큰의 만료기간을 DATE형으로 토큰에 추가(exp라는 클레임으로 long형으로 토큰에 추가)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        log.info("[TokenProvider] generateTokenDTO End =============== ");

        return new TokenDTO(BEARER_TYPE, member.getMemberId(), member.getMemberCode(), accessToken, accessTokenExpiresIn.getTime(),"");
    }

    /* 2. 토큰에 등록된 클래임의 subject에서 해당 회원의 아이디를 추출 */

    public String getUseId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()      // payload의 claims 추출
                .getSubject();  // Claims 중에 등록 클레임에 해당하는 sub값 추출 (회원 아이디)
    }



    /* 3. AccessToken으로 인증 객체 추출 */

    public Authentication getAuthentication(String token) {

        log.info("[TokenProvider] generateTokenDTO Start =============== ");
        // 5번 메소드 재사용할 거임
        /* 토큰을 claim들을 추출(토큰 복호화)*/
        Claims claims = parseClaims(token);
        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        /* 클레임에서 권한 정보 가져오기  */

        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUseId(token));

        log.info("[TokenProvider] generateTokenDTO End =============== ");
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /* 4. 토큰 유효성 검사 */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;

        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("[TokenProvider] 잘못된 JWT 서명입니다.");
            throw new TokenException("잘못된 JWT 서명입니다.");
        }catch (ExpiredJwtException e){
            log.info("[TokenProvider] 만료된 JWT 서명입니다.");
            throw new TokenException("만료된 JWT 서명입니다.");
        }catch (UnsupportedJwtException e){
            log.info("[TokenProvider] 지원되지 않는 JWT 서명입니다.");
            throw new TokenException("지원되지 않는 JWT 서명입니다.");
        }catch (IllegalArgumentException e){
            log.info("[TokenProvider] JWT 토큰이 잘못되었습니다.");
            throw new TokenException("JWT 토큰이 잘못되었습니다.");
        }
    }

    /* 5. AccessToken에서 클래임 추출하는 메소드 */

    public Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e) {
            return e.getClaims(); // 토큰이 만료되어 예외가 발생하더라도 클레임 값들을 뽑을 수 있다

        }
    }








}
