package com.sshyu.zibnote.domain.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class NaverUserInfo {
    
    private String resultcode;
    private String message;
    private Response response = new Response();

    public static NaverUserInfo of(
        final String resultcode, 
        final String message, 
        final String id,
        final String nickname,
        final String name,
        final String email
    ) {
        NaverUserInfo naverUserInfo = new NaverUserInfo();

        Response response = naverUserInfo.new Response();
        response.setId(id);
        response.setNickname(nickname);
        response.setName(name);
        response.setEmail(email);

        naverUserInfo.setResultcode(resultcode);
        naverUserInfo.setMessage(message);
        naverUserInfo.setResponse(response);

        return naverUserInfo;
    }

    @NoArgsConstructor @AllArgsConstructor
    @Getter @Setter
    public class Response {
        private String id;
        private String nickname;
        private String name;
        private String email;
    }

}
