package com.bank.ng.openBanking.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class TokenResponseDTO {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private String expiresIn;
    private String scope;
    private String user_seq_no;
    private String memberId;

    @Builder

    public TokenResponseDTO(String accessToken, String tokenType, String refreshToken, String expiresIn, String scope, String user_seq_no, String memberId) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.scope = scope;
        this.user_seq_no = user_seq_no;
        this.memberId = memberId;
    }
}
