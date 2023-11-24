package com.bank.ng.openBanking.services;


import com.bank.ng.openBanking.dto.TokenRequestDTO;
import com.bank.ng.openBanking.dto.TokenResponseDTO;
import com.bank.ng.openBanking.entity.OB_User;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenBankingService {

    @Value("${OB_tokenRequestURL}")
    private String reqTokenURL;
    @Value("${OB_client_key}")
    private String client_key;

    //    private final TestRepository testRepository;
    private final ModelMapper mapper;

    public Object RandomGenerator() {

        log.info("----- OAuthService : RandomGenerator -----");
        SecureRandom secureRandom = new SecureRandom();

        // 32 바이트 (256 비트)의 난수 생성
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);

        // 16진수 문자열로 변환
        StringBuilder sb = new StringBuilder();
        for (byte b : randomBytes) {
            sb.append(String.format("%02x", b));
        }

        String randomHex = sb.toString();


        return randomHex;

    }

    public Object requestToken(TokenRequestDTO tokenRequest) {
        // Authorization Code로 AccessToken 요청

        // 파라미터 설정
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("code", tokenRequest.getCode());
        parameters.add("client_id", tokenRequest.getClientId());
        parameters.add("client_secret", client_key);
        parameters.add("redirect_uri", tokenRequest.getRedirectUri());
        parameters.add("grant_type", "authorization_code");

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // 요청 엔터티 생성
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);

        // RestTemplate을 사용하여 AccessToken 요청
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(reqTokenURL, requestEntity, String.class);

        // 응답 확인
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            System.out.println("response body : " + responseBody);
            // responseBody를 원하는 형태로 처리

            // json 응답객체 파싱
            TokenResponseDTO tokenResponse = convertResponseBody(responseBody);
            tokenResponse.setMemberId(tokenRequest.getMemberId());

//            testRepository.save(mapper.map(tokenResponse, OB_User.class));


            return tokenResponse;

        } else {
            System.err.println("Access Token 요청 실패: " + responseEntity.getStatusCode());

            return "code=400";
        }

    }

    private TokenResponseDTO convertResponseBody(String responseBody) {

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(responseBody);

        return TokenResponseDTO.builder()
                .accessToken(element.getAsJsonObject().get("access_token").getAsString())
                .tokenType(element.getAsJsonObject().get("token_type").getAsString())
                .refreshToken(element.getAsJsonObject().get("refresh_token").getAsString())
                .expiresIn(element.getAsJsonObject().get("expires_in").getAsString())
                .scope(element.getAsJsonObject().get("scope").getAsString())
                .user_seq_no(element.getAsJsonObject().get("user_seq_no").getAsString())
                .build();
    }
}