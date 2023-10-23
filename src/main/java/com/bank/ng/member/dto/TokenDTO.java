package com.bank.ng.member.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenDTO {

    private String grantType; // 토큰 타입
    private String memberId;
    private String accessToken; // 엑세스 토큰
    private Long accessTokenExpiresIn; // 만료시간

}
