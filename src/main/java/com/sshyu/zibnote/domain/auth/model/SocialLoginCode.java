package com.sshyu.zibnote.domain.auth.model;

import com.sshyu.zibnote.domain.member.exception.SocialLoginException;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder @Getter @Setter
public class SocialLoginCode {

    private String code;
    private String error;
    private String errorDescription;
    private String state;

    public void logFields(SocialLoginType socialLoginType) {
        log.info(socialLoginType + " Social Login Callback: code={}, error={}, error_description={}, state={}", 
            this.code, this.getError(), this.getErrorDescription(), this.getState());
    }

    public void verify() {
        if (this.code == null) {
            throw new SocialLoginException("[Social Login] 로그인 인증 code 받는 중 예외 발생");
        }
    }

}
