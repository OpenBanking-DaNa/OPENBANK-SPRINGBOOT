package com.bank.ng.member.repository;

import com.bank.ng.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    // 회원 아이디로 조회하기
    Member findByMemberId(String memberId);
}
