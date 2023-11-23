package com.bank.ng.member.service;

import com.bank.ng.exception.LoginFailedException;
import com.bank.ng.jwt.TokenProvider;
import com.bank.ng.member.dto.MemberDTO;
import com.bank.ng.member.dto.TokenDTO;
import com.bank.ng.member.entity.Member;
import com.bank.ng.member.entity.OAuth;
import com.bank.ng.member.repository.MemberRepository;
import com.bank.ng.member.repository.OAuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final OAuthRepository oAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final ModelMapper modelMapper;

    public MemberService(
            MemberRepository memberRepository,
            OAuthRepository oAuthRepository,
            PasswordEncoder passwordEncoder,
            TokenProvider tokenProvider,
            ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.oAuthRepository = oAuthRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.modelMapper = modelMapper;
    }


    // 회원 등록
    @Transactional
    public Object signup(MemberDTO memberDTO) {

        log.info("[MemberService] signup Start ========================== ");
        log.info("[MemberService] signup - TokenDTO {} ", memberDTO);

        Member registMember = modelMapper.map(memberDTO, Member.class);

        // 평문의 암호문자열을 암호화 시켜서 전달
        registMember.setMemberPassword(passwordEncoder.encode(registMember.getMemberPassword()));
        // 반환형이 member
        Member memberResult = memberRepository.save(registMember);

        log.info("[MemberService] signup - memberResult {} ", memberResult);

        return null;
    }


    // 로그인

    public Object login(MemberDTO memberDTO){

        log.info("[MemberService] login Start ========================== ");
        log.info("[MemberService] login - TokenDTO {} ", memberDTO);

        // 1. 아이디 조회
        Member member = memberRepository.findByMemberId(memberDTO.getMemberId());

        System.out.println("member" + member);

        log.info("[MemberService] login - member 조회 {} ===============", member);

        if(member == null){
            throw new LoginFailedException(memberDTO.getMemberId() + "를 찾을 수 없습니다.");
        }

        // 2. 비밀번호 매칭 (passwordEncoder.matches(평문, 다이제스트))
        if(!passwordEncoder.matches(memberDTO.getMemberPassword(), member.getMemberPassword())){
            log.info("[AuthService] login - Password Match Fail!");
            throw new LoginFailedException("잘못된 비밀번호 입니다.");
        }

        // 3. 토큰 발급
        TokenDTO tokenDTO = tokenProvider.generateTokenDTO(member);
        log.info("[MemberService] login - TokenDTO {} ", tokenDTO);


        // 4. clientId 추가
        int memberCode = member.getMemberCode();

        System.out.println("memberCode : " + memberCode);

        OAuth oAuth = oAuthRepository.findClientIdByMemberCode(memberCode);
        System.out.println("oAuth : " + oAuth);


        tokenDTO.setClientId(oAuth.getClientId());

        log.info("[MemberService] login End ===========================");
        return tokenDTO;
    }
}
