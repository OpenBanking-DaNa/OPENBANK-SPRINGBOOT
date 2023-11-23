package com.bank.ng.member.controller;

import com.bank.ng.common.ResponseDTO;
import com.bank.ng.member.dto.MemberDTO;
import com.bank.ng.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원등록
    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(@RequestBody MemberDTO memberDTO){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "회원 등록 성공", memberService.signup(memberDTO)));
    }


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody MemberDTO memberDTO){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그인 성공", memberService.login(memberDTO)));
    }

//    // 로그아웃
//    @PostMapping("/logout")
//    public ResponseEntity<ResponseDTO> logout(@RequestBody MemberDTO memberDTO){
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그아웃 성공", memberService.logout(memberDTO)));
//    }

}
