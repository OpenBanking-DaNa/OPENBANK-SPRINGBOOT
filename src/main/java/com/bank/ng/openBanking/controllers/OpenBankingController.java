package com.bank.ng.openBanking.controllers;


import com.bank.ng.common.ResponseDTO;
import com.bank.ng.openBanking.dto.TokenRequestDTO;
import com.bank.ng.openBanking.dto.TokenResponseDTO;
import com.bank.ng.openBanking.services.OpenBankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class OpenBankingController {

    private final OpenBankingService openBankingService;


//    @PostMapping("/api/oauth/token")
//    public TokenResponseDTO requestToken(@RequestBody TokenRequestDTO tokenRequest){
//
//        log.info("-------- OpenBankingController : requestToken {} ", tokenRequest);
//
//
//        return openBankingService.requestToken(tokenRequest);
//    }

    @PostMapping("/api/oauth/token")
    public ResponseEntity<ResponseDTO> requestToken(@RequestBody TokenRequestDTO tokenRequest){

        log.info("-------- OpenBankingController : requestToken {} ", tokenRequest);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,
                "사용자 토큰 발급 요청", openBankingService.requestToken(tokenRequest)));
    }

}



