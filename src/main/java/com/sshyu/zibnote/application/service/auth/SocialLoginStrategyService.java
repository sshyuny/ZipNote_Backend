package com.sshyu.zibnote.application.service.auth;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sshyu.zibnote.domain.auth.model.SocialLoginCode;
import com.sshyu.zibnote.domain.auth.model.SocialLoginType;
import com.sshyu.zibnote.domain.auth.port.in.SocialLoginUseCase;
import com.sshyu.zibnote.domain.common.exception.ZibnoteRuntimeException;

@Service
public class SocialLoginStrategyService {
    
    private final Map<SocialLoginType, SocialLoginUseCase> strategyMap;

    public SocialLoginStrategyService(List<SocialLoginUseCase> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(
                    s -> s.getSupportedType(), 
                    Function.identity()
                ));
    }

    public void doSocialLogin(SocialLoginType socialLoginType, SocialLoginCode socialLoginCode) {
        SocialLoginUseCase socialLoginUseCase = strategyMap.get(socialLoginType);
        if (socialLoginUseCase == null) {
            throw new ZibnoteRuntimeException("지원하지 않는 소셜 로그인 타입");
        }
        socialLoginUseCase.doSocialLogin(socialLoginCode);
    }

}
