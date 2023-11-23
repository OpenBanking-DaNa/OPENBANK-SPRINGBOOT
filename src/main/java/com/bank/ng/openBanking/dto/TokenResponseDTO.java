package com.bank.ng.openBanking.dto;

import lombok.Data;

@Data
public class TokenResponseDTO {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
    private String memberCode;
}
