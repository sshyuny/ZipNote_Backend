package com.sshyu.zibnote.adapter.out.web.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KakaoAccessTokenHttpBodyDto {
    
    private String access_token;
    private String refresh_token;
    private String token_type;
    private int expires_in;
    private String scope;

}
