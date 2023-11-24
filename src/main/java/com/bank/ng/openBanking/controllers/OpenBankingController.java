package com.bank.ng.openBanking.controllers;


import com.bank.ng.common.ResponseDTO;
import com.bank.ng.openBanking.dto.TokenRequestDTO;
import com.bank.ng.openBanking.services.OpenBankingBankService;
import com.bank.ng.openBanking.services.OpenBankingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping("/api/oauth")
public class OpenBankingController {

    private final OpenBankingService openBankingService;
    private final OpenBankingBankService openBankingBankService;
    public OpenBankingController(OpenBankingService openBankingService, OpenBankingBankService openBankingBankService) {
        this.openBankingService = openBankingService;
        this.openBankingBankService = openBankingBankService;
    }

    @PostMapping("/token")
    public ResponseEntity<ResponseDTO> requestToken(@RequestBody TokenRequestDTO tokenRequest){
        log.info("-------- OpenBankingController : requestToken {} ", tokenRequest);


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,
                "사용자 토큰 발급 요청", openBankingService.requestToken(tokenRequest)));
    }

    @GetMapping("/secret/{memberCode}")
    public ResponseEntity<ResponseDTO> getClientSecret(@PathVariable int memberCode){

        log.info("----- OpenBankingController : getClientSecret start");
        log.info("----- OpenBankingController : memberCode {}", memberCode);

        Object clientSecret = openBankingBankService.getClientSecret(memberCode);
        log.info("----- OpenBankingController : clientSecret {}", clientSecret);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "성공", clientSecret));
    }

}



