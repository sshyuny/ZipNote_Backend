package com.sshyu.zibnote.adapter.out.web.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class NaverUserInfoHttpBodyDto {
    
    private String resultcode;
    private String message;
    private Response response;

    @NoArgsConstructor @AllArgsConstructor
    @Getter @Setter
    public class Response {
        private String id;
        private String nickname;
        private String name;
        private String email;
    }

}
