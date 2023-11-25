package com.bank.ng.openBanking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_OB_USER")
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
    private String accessToken;
    @Column(name = "TOKEN_TYPE")
    private String tokenType;
    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;
    @Column(name = "EXPIRES_IN")
    private String expiresIn;
    @Column(name = "SCOPE")
    private String scope;

}
