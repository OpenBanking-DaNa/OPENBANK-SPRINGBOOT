package com.bank.ng.member.repository;

import com.bank.ng.member.entity.Member;
import com.bank.ng.member.entity.OAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthRepository extends JpaRepository<OAuth, Integer> {


    // 회원코드로 cilentId 조회
    OAuth findClientIdByMemberCode(int memberCode);
}
