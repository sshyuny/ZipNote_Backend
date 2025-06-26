package com.sshyu.zibnote.domain.auth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class SocialLoginAccessToken {
    
    // Common
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private int expiresIn;

    // Kakao
    private String scope;

    // Naver
    private String error;
    private String errorDescription;

}
