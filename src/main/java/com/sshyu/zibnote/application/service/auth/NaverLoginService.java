package com.sshyu.zibnote.application.service.auth;

import org.springframework.stereotype.Service;

import com.sshyu.zibnote.domain.auth.model.SocialLoginAccessToken;
import com.sshyu.zibnote.domain.auth.model.SocialLoginCode;
import com.sshyu.zibnote.domain.auth.model.SocialLoginType;
import com.sshyu.zibnote.domain.auth.port.in.SocialLoginUseCase;
import com.sshyu.zibnote.domain.auth.port.out.SocialLoginApiPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NaverLoginService implements SocialLoginUseCase {

    private final SocialLoginApiPort socialLoginApiPort;

    @Override
    public SocialLoginType getSupportedType() {
        return SocialLoginType.NAVER;
    }

    @Override
    public void doSocialLogin(SocialLoginCode socialLoginCode) {

        socialLoginCode.logFields(getSupportedType());
        socialLoginCode.verify();

        SocialLoginAccessToken accessToken = socialLoginApiPort.requestNaverAccessToken(socialLoginCode.getCode());
        socialLoginApiPort.requestNaverUserInfo(accessToken.getAccessToken());
    }

}
