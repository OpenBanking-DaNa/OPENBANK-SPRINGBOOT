package com.bank.ng.openBanking.controllers;


import com.bank.ng.common.ResponseDTO;
import com.bank.ng.openBanking.dto.TokenRequestDTO;
import com.bank.ng.openBanking.dto.TokenResponseDTO;
import com.bank.ng.openBanking.services.OpenBankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/oauth")
public class OpenBankingController {

    private final OpenBankingService openBankingService;

    public OpenBankingController(OpenBankingService openBankingService) {
        this.openBankingService = openBankingService;
    }

    @GetMapping("/secret/{memberCode}")
    public ResponseEntity<ResponseDTO> getClientSecret(@PathVariable int memberCode){

        log.info("----- OpenBankingController : getClientSecret start");
        log.info("----- OpenBankingController : memberCode {}", memberCode);

        Object clientSecret = openBankingService.getClientSecret(memberCode);
        log.info("----- OpenBankingController : clientSecret {}", clientSecret);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "성공", clientSecret));
    }


    @PostMapping("/token")
    public TokenResponseDTO requestToken(@RequestBody TokenRequestDTO tokenRequest){

        log.info("-------- OpenBankingController : requestToken {} ", tokenRequest);


        return openBankingService.requestToken(tokenRequest);
    }

}



