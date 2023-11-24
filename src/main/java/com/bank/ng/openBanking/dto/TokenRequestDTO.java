package com.bank.ng.openBanking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
public class TokenRequestDTO {
    private String code;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String grantType;

    private String memberId;
}
