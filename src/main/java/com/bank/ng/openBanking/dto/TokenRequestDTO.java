package com.bank.ng.openBanking.dto;

import lombok.Data;

@Data
public class TokenRequestDTO {
    private String code;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String grantType;
}
