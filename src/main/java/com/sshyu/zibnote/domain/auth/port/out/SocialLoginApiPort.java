package com.sshyu.zibnote.domain.auth.port.out;

import com.sshyu.zibnote.domain.auth.model.KakaoUserInfo;
import com.sshyu.zibnote.domain.auth.model.NaverUserInfo;
import com.sshyu.zibnote.domain.auth.model.SocialLoginAccessToken;

public interface SocialLoginApiPort {
    
    SocialLoginAccessToken requestNaverAccessToken(String code);

    SocialLoginAccessToken requestKakaoAccessToken(String code);

    KakaoUserInfo requestKakaoUserInfo(String accessToken);

    NaverUserInfo requestNaverUserInfo(String accessToken);

}
