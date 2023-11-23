package com.bank.ng.member.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OAuthDTO {

    private String clientId;
    private String state;
    private int memberCode;
    private String authorizationCode;
    private String clientSecret;

}
