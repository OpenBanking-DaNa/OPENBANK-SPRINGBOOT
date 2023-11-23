package com.bank.ng.openBanking.dto;

import lombok.Data;

@Data
public class TokenRequestDTO {
    private String code;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String grant_type;
}
