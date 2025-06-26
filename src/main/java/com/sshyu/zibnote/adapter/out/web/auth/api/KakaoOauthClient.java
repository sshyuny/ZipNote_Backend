package com.sshyu.zibnote.adapter.out.web.auth.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.sshyu.zibnote.adapter.out.web.auth.dto.KakaoAccessTokenHttpBodyDto;
import com.sshyu.zibnote.adapter.out.web.auth.dto.KakaoUserInfoHttpBodyDto;
import com.sshyu.zibnote.adapter.out.web.auth.mapper.SocialLoginHttpBodyMapper;
import com.sshyu.zibnote.domain.auth.model.KakaoUserInfo;
import com.sshyu.zibnote.domain.auth.model.SocialLoginAccessToken;

@Component
public class KakaoOauthClient {

    private String kakaoTokenRequestUrl = "https://kauth.kakao.com/oauth/token";
    private String kakaoUserInfoRequestUrl = "https://kapi.kakao.com/v2/user/me";

    private String kakaoGrantType = "authorization_code";
    @Value("${auth.kakao.client_id}")
    private String kakaoClientId;
    @Value("${auth.kakao.redirect_uri}")
    private String kakaoRedirectUri;

    public SocialLoginAccessToken requestAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", kakaoGrantType);
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", kakaoRedirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<KakaoAccessTokenHttpBodyDto> response = restTemplate.postForEntity(
            kakaoTokenRequestUrl,
            request,
            KakaoAccessTokenHttpBodyDto.class
        );

        KakaoAccessTokenHttpBodyDto resBody = response.getBody();

        return SocialLoginHttpBodyMapper.toDomain(resBody);
    }

    public KakaoUserInfo requestUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoUserInfoHttpBodyDto> response = restTemplate.exchange(
            kakaoUserInfoRequestUrl,
            HttpMethod.GET,
            entity,
            KakaoUserInfoHttpBodyDto.class
        );

        KakaoUserInfoHttpBodyDto resBody = response.getBody();

        return SocialLoginHttpBodyMapper.toDomain(resBody);
    }

}
