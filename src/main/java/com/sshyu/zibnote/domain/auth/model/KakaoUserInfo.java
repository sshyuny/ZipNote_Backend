package com.sshyu.zibnote.domain.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class KakaoUserInfo {
    
    private String id;
    private String hasSignedUp;
    private String connectedAt;
    private String synchedAt;
    private String properties;
    private KakaoAccount kakaoAccount;

    public static KakaoUserInfo of(
        final String id, 
        final String hasSignedUp,
        final String connectedAt,
        final String synchedAt,
        final String properties,
        final String profileNeedsAgreement,
        final String profileNicknameNeedsAgreement
    ) {
        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo();

        KakaoAccount kakaoAccount = kakaoUserInfo.new KakaoAccount();
        kakaoAccount.setProfileNeedsAgreement(profileNeedsAgreement);
        kakaoAccount.setProfileNicknameNeedsAgreement(profileNicknameNeedsAgreement);

        kakaoUserInfo.setId(id);
        kakaoUserInfo.setHasSignedUp(hasSignedUp);
        kakaoUserInfo.setConnectedAt(connectedAt);
        kakaoUserInfo.setSynchedAt(synchedAt);
        kakaoUserInfo.setProperties(properties);
        kakaoUserInfo.setKakaoAccount(kakaoAccount);

        return kakaoUserInfo;
    }

    @NoArgsConstructor @AllArgsConstructor
    @Getter @Setter
    public class KakaoAccount {
        private String profileNeedsAgreement;
        private String profileNicknameNeedsAgreement;
    }

}
