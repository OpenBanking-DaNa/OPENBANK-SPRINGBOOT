package com.bank.ng.openBanking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@ToString
public class OB_User {

    @Id
    @Column(name = "USER_SEQ_NO")
    private String user_seq_no;
    @Column(name = "MEMBER_ID")
    private String memberId;
    @Column(name = "ACCESS_TOKEN")
    private String access_token;
    @Column(name = "TOKEN_TYPE")
    private String token_type;
    @Column(name = "REFRESH_TOKEN")
    private String refresh_token;
    @Column(name = "EXPIRES_IN")
    private String expires_in;
    @Column(name = "SCOPE")
    private String scope;

}
