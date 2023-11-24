package com.bank.ng.openBanking.services;

import com.bank.ng.member.entity.OAuth;
import com.bank.ng.member.repository.OAuthRepository;
import com.bank.ng.openBanking.dto.TokenRequestDTO;
import com.bank.ng.openBanking.dto.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Slf4j
public class OpenBankingService {

    private final OAuthRepository oAuthRepository;

    public OpenBankingService(OAuthRepository oAuthRepository) {
        this.oAuthRepository = oAuthRepository;
    }

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

    public Object getClientSecret(int memberCode) {
        log.info("===== OpenBankingService : getClientSecret start");

        OAuth clientSecret = oAuthRepository.findClientSecretByMemberCode(memberCode);

        log.info("===== OpenBankingService : clientSecret {}", clientSecret);

        log.info("===== OpenBankingService : getClientSecret end");
        return clientSecret;
    }


    public TokenResponseDTO requestToken(TokenRequestDTO tokenRequest) {
        log.info("===== OpenBankingService : requestToken");
        return null;
    }

}
