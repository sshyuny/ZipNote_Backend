package com.sshyu.zibnote.domain.auth.port.in;

import com.sshyu.zibnote.domain.auth.model.SocialLoginCode;
import com.sshyu.zibnote.domain.auth.model.SocialLoginType;

public interface SocialLoginUseCase {

    SocialLoginType getSupportedType();
    
    void doSocialLogin(SocialLoginCode socialLoginCode);

}
