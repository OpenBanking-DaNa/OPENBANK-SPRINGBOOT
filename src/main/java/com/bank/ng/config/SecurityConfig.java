package com.bank.ng.config;


import com.bank.ng.jwt.JwtAccessDeniedHandler;
import com.bank.ng.jwt.JwtAuthenticationEntryPoint;
import com.bank.ng.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity // security로 다룰 거임
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    /* 1. 암호화 처리를 위한 PasswordEncoder를 빈으로 설정 */

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* 2. Security 설정을 무시할 정적 리소스 등록 */

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**",
                "/lib/**", "productimgs/**");
    }

    /* 3. HTTP요청에 대한 권한별 설정(기존 세션 인증 -> 토큰 인증으로 변경함 :> 추가적으로 코드가 더 생김) */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                /* 기본 시큐리티 설정에서 JWT토큰과 관련된 유효성과 권한 체크용 설정 */
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 필요한 권한 없이 접근 -> 403
                .accessDeniedHandler(jwtAccessDeniedHandler)// 유효한 자격 증명이 없음 -> 401
                .and()
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // cors를 위해 preflight 요청 처리용 option 요청 허용
                .antMatchers("/**").permitAll() // 로그인할 때는 모든 권한 허용
//                .antMatchers("/api/member/**").permitAll()
//                .antMatchers("/api/**").hasAnyRole("USER", "ADMIN") // 위 두줄이 이 한줄로도 가능
                    .anyRequest().permitAll() // 어떤요청이든 허용. 구현 전에 추가
                .and()
                /* 세션 인증 방식을 쓰지 않겠다는 설정 */
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .cors()
                .and()
                /* JWT 토큰 방식을 쓰겠다는 설정 */
                    .apply(new JwtSecurityConfig(tokenProvider));
        return http.build();

    }
    /* preflight?
     * 요청할 url이 외부 도메인일 경우 웹 브라우저에서 자체 실행. options 메소드로 사전 요청을 보냄
     * 사전에 요청이 안전한지 확인하기 위함(유효한지 서버에 미리 파악할 수 있도록 보내는 수단을 의미함)
     * */


    /* 4. CORS(Cross-origin-resource-sharing) 설정용 Bean */

// 리엑트가 부트한테 보내주는 거

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Content-type"
                , "Access-Control-Allow-Headers", "Authorization"
                , "X-Requested-With"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // url 전체 설정
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
