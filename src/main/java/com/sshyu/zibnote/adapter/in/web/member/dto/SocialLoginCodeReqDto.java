package com.sshyu.zibnote.adapter.in.web.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SocialLoginCodeReqDto {
    
    private String code;
    private String error;
    private String error_description;
    private String state;

}
