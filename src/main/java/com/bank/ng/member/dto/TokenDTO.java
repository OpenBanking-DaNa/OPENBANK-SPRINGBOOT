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
    private int memberCode;
    private String accessToken; // 엑세스 토큰
    private Long accessTokenExpiresIn; // 만료시간

    private String clientId;

//    public TokenDTO(String grantType,String memberId,String accessToken,Long accessTokenExpiresIn){
//        this.grantType = grantType;
//        this.memberId = memberId;
//        this.accessToken = accessToken;
//        this.accessTokenExpiresIn = accessTokenExpiresIn;
//    }
}
