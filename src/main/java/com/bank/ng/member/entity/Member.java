package com.bank.ng.member.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_MEMBER")
@SequenceGenerator(
        name = "MEMBER_SEQ",
        sequenceName = "SEQ_MEMBER_CODE",
        initialValue = 1, allocationSize = 1
)
public class Member {

    @Id
    @Column(name = "MEMBER_CODE")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ"
    )
    private int memberCode;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "MEMBER_PASSWORD")
    private String memberPassword;
}
