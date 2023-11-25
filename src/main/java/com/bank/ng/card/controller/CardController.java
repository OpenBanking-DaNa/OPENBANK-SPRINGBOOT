package com.bank.ng.card.controller;


import com.bank.ng.card.service.CardService;
import com.bank.ng.common.ResponseDTO;
import com.bank.ng.openBanking.dto.TokenRequestDTO;
import com.bank.ng.openBanking.services.OpenBankingBankService;
import com.bank.ng.openBanking.services.OpenBankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/card")
public class CardController {

    private final CardService cardService;

    @GetMapping("/info")
    public ResponseEntity<ResponseDTO> getCardInfo(String userSeqNo){


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,
                "카드 기본정보 조회 요청", cardService.getCardInfo(userSeqNo)));    }

}



