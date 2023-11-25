package com.bank.ng.card.service;

import com.bank.ng.openBanking.dto.TokenResponseDTO;
import com.bank.ng.openBanking.entity.OB_User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {


    public Object getCardInfo(String userSeqNo) {

//        // 파라미터 설정
//        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("bank_tran_id", tokenRequest.getCode());
//        parameters.add("user_seq_no", tokenRequest.getClientId());
//        parameters.add("bank_code_std", client_key);
//        parameters.add("member_bank_code", tokenRequest.getRedirectUri());
//
//        // 헤더 설정
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "");
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//
//        // 요청 엔터티 생성
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);
//
//        // RestTemplate을 사용하여 AccessToken 요청
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(reqTokenURL, requestEntity, String.class);
//
//        // 응답 확인
//        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//            String responseBody = responseEntity.getBody();
//            System.out.println("response body : " + responseBody);
//            // responseBody를 원하는 형태로 처리
//
//            // json 응답객체 파싱
//            TokenResponseDTO tokenResponse = convertResponseBody(responseBody);
//            tokenResponse.setMemberId(tokenRequest.getMemberId());
//            log.info("----- OAuthService : RandomGenerator -----tokenResponse {}", tokenResponse);
//
//            OB_User obUser = mapper.map(tokenResponse, OB_User.class);
//            log.info("----- OAuthService : RandomGenerator -----obUser {}", obUser);
//
//            // 유저 토큰정보 DB insert
//            obRepository.save(obUser);
//
//            return tokenResponse;
//
//        } else {
//            System.err.println("Access Token 요청 실패: " + responseEntity.getStatusCode());
//
//            return "code=400";
//        }

        return null;

    }
}
