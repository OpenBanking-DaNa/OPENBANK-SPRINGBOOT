package com.bank.ng.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class AccountController {

    private final String OPEN_BANKING_API_URL = "URL";
    private final RestTemplate restTemplate;

    public AccountController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/api/accounts")
    public String getAccounts(){
        // 오픈뱅킹 API호출 후 데이터 반환하는 로직

        return "ok";
    }
}
