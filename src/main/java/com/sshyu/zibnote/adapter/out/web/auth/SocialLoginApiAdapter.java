package com.sshyu.zibnote.adapter.out.web.auth;

import org.springframework.stereotype.Component;

import com.sshyu.zibnote.adapter.out.web.auth.api.KakaoOauthClient;
import com.sshyu.zibnote.adapter.out.web.auth.api.NaverOauthClient;
import com.sshyu.zibnote.domain.auth.model.KakaoUserInfo;
import com.sshyu.zibnote.domain.auth.model.NaverUserInfo;
import com.sshyu.zibnote.domain.auth.model.SocialLoginAccessToken;
import com.sshyu.zibnote.domain.auth.port.out.SocialLoginApiPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SocialLoginApiAdapter implements SocialLoginApiPort {

    private final NaverOauthClient naverOauthClient;
    private final KakaoOauthClient kakaoOauthClient;

    @Override
    public SocialLoginAccessToken requestKakaoAccessToken(String code) {
        return kakaoOauthClient.requestAccessToken(code);
    }

    @Override
    public KakaoUserInfo requestKakaoUserInfo(String accessToken) {
        return kakaoOauthClient.requestUserInfo(accessToken);
    }

    @Override
    public SocialLoginAccessToken requestNaverAccessToken(String code) {
        return naverOauthClient.requestAccessToken(code);
    }

    @Override
    public NaverUserInfo requestNaverUserInfo(String accessToken) {
        return naverOauthClient.requestUserInfo(accessToken);
    }

}
