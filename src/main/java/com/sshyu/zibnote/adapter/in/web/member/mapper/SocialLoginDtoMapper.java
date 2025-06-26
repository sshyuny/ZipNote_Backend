package com.sshyu.zibnote.adapter.in.web.member.mapper;

import com.sshyu.zibnote.adapter.in.web.member.dto.SocialLoginCodeReqDto;
import com.sshyu.zibnote.domain.auth.model.SocialLoginCode;

public class SocialLoginDtoMapper {
    
    public static SocialLoginCode toDomain(SocialLoginCodeReqDto reqDto) {
        return SocialLoginCode.builder()
                    .code(reqDto.getCode())
                    .error(reqDto.getError())
                    .errorDescription(reqDto.getError_description())
                    .state(reqDto.getState())
                    .build();
    }
}
