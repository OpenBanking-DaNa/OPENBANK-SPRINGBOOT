package com.bank.ng.member.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO{

    private int memberCode;

    private String memberId;

    private String memberPassword;

}
