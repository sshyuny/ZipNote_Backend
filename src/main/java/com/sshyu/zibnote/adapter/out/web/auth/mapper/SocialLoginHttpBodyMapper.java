package com.sshyu.zibnote.adapter.out.web.auth.mapper;

import com.sshyu.zibnote.adapter.out.web.auth.dto.KakaoAccessTokenHttpBodyDto;
import com.sshyu.zibnote.adapter.out.web.auth.dto.KakaoUserInfoHttpBodyDto;
import com.sshyu.zibnote.adapter.out.web.auth.dto.NaverAccessTokenHttpBodyDto;
import com.sshyu.zibnote.adapter.out.web.auth.dto.NaverUserInfoHttpBodyDto;
import com.sshyu.zibnote.domain.auth.model.KakaoUserInfo;
import com.sshyu.zibnote.domain.auth.model.NaverUserInfo;
import com.sshyu.zibnote.domain.auth.model.SocialLoginAccessToken;

public class SocialLoginHttpBodyMapper {
    
    public static SocialLoginAccessToken toDomain(NaverAccessTokenHttpBodyDto dto) {
        return SocialLoginAccessToken.builder()
                    .accessToken(dto.getAccess_token())
                    .refreshToken(dto.getRefresh_token())
                    .tokenType(dto.getToken_type())
                    .expiresIn(dto.getExpires_in())
                    .error(dto.getError())
                    .errorDescription(dto.getError_description())
                    .build();
    }

    public static SocialLoginAccessToken toDomain(KakaoAccessTokenHttpBodyDto dto) {
        return SocialLoginAccessToken.builder()
                    .accessToken(dto.getAccess_token())
                    .refreshToken(dto.getRefresh_token())
                    .tokenType(dto.getToken_type())
                    .expiresIn(dto.getExpires_in())
                    .scope(dto.getScope())
                    .build();
    }

    public static NaverUserInfo toDomain(NaverUserInfoHttpBodyDto dto) {
        return NaverUserInfo.of(
            dto.getResultcode(),
            dto.getMessage(),
            dto.getResponse().getId(),
            dto.getResponse().getNickname(),
            dto.getResponse().getName(),
            dto.getResponse().getEmail()
        );
    }

    public static KakaoUserInfo toDomain(KakaoUserInfoHttpBodyDto dto) {
        return KakaoUserInfo.of(
            dto.getId(), 
            dto.getHas_signed_up(), 
            dto.getConnected_at(), 
            dto.getSynched_at(), 
            dto.getProperties(), 
            dto.getKakao_account().getProfile_needs_agreement(), 
            dto.getKakao_account().getProfile_nickname_needs_agreement()
        );
    }

}
