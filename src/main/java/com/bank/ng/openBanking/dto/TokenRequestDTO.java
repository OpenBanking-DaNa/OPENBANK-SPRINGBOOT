package com.bank.ng.openBanking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
public class TokenRequestDTO {
    private String code;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String grant_type;

    private String memberId;

}
