package com.bank.ng.openBanking.dto;

import lombok.Data;

@Data
public class TokenResponseDTO {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private String expiresIn;
    private String scope;
    private String memberCode;
}
