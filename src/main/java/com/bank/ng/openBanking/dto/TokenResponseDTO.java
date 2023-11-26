package com.bank.ng.openBanking.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class TokenResponseDTO {
//    private String accessToken;
//    private String tokenType;
//    private String refreshToken;
//    private String expiresIn;
    private String scope;
    private String user_seq_no;

    @Builder

    public TokenResponseDTO(String scope, String user_seq_no) {

        this.scope = scope;
        this.user_seq_no = user_seq_no;
    }
}
