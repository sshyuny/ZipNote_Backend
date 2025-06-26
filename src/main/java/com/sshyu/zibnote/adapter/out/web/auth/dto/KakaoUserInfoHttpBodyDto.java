package com.sshyu.zibnote.adapter.out.web.auth.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class KakaoUserInfoHttpBodyDto {
    
    private String id;
    private String has_signed_up;
    private String connected_at;
    private String synched_at;
    private String properties;
    private KakaoAccount kakao_account;
    private Map<String, String> for_partner;

    @NoArgsConstructor @AllArgsConstructor
    @Getter @Setter
    public class KakaoAccount {
        private String profile_needs_agreement;
        private String profile_nickname_needs_agreement;
    }

}
