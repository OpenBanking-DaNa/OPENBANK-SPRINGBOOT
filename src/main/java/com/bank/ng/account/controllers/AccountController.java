package com.bank.ng.account.controllers;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class AccountController {

    private final String OPEN_BANKING_API_URL = "https://openapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num";

}



