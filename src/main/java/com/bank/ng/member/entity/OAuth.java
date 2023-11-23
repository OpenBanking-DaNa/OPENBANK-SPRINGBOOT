package com.bank.ng.member.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_OAUTH")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OAuth {

    @Id
    @Column(name = "CLIENT_ID")
    private String clientId;

    @Column(name = "STATE")
    private String state;

    @Column(name = "MEMBER_CODE")
    private int memberCode;

    @Column(name = "AUTHORIZATION_CODE")
    private String authorizationCode;

    @Column(name = "CLIENT_SECRET")
    private String clientSecret;

}
