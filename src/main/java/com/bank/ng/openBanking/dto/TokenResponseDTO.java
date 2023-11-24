package com.bank.ng.openBanking.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class TokenResponseDTO {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
    private String user_seq_no;
    private String memberId;

    @Builder

    public TokenResponseDTO(String access_token, String token_type, String refresh_token, String expires_in, String scope, String user_seq_no, String memberId) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.scope = scope;
        this.user_seq_no = user_seq_no;
        this.memberId = memberId;
    }
}
