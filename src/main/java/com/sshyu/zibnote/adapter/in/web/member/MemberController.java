package com.sshyu.zibnote.adapter.in.web.member;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sshyu.zibnote.adapter.in.web.common.res.ApiResponse;
import com.sshyu.zibnote.adapter.in.web.common.res.ResponseCode;
import com.sshyu.zibnote.adapter.in.web.common.res.ResponseMessage;
import com.sshyu.zibnote.adapter.in.web.member.dto.LoginReqDto;
import com.sshyu.zibnote.adapter.in.web.member.dto.SocialLoginCodeReqDto;
import com.sshyu.zibnote.adapter.in.web.member.mapper.SocialLoginDtoMapper;
import com.sshyu.zibnote.application.service.auth.SocialLoginStrategyService;
import com.sshyu.zibnote.domain.auth.model.SocialLoginType;
import com.sshyu.zibnote.domain.auth.model.Token;
import com.sshyu.zibnote.domain.auth.port.in.AuthUseCase;
import com.sshyu.zibnote.domain.member.model.Member;
import com.sshyu.zibnote.domain.member.port.in.MemberUseCase;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberUseCase memberUseCase;
    private final AuthUseCase authUseCase;
    private final SocialLoginStrategyService socialLoginStrategyService;

    @Value("${auth.front.redirect-uri}")
    private String frontRedirectUri;

    @PostMapping("/pass/api/login")
    public ResponseEntity<ApiResponse<Void>> login(HttpSession httpSession, @RequestBody LoginReqDto loginReqDto) {

        final Token token = authUseCase.login(loginReqDto.getName());
        log.info("로그인 성공! " + loginReqDto.getName());

        return ResponseEntity
                    .ok()
                    .header("Authorization", "Bearer " + token.getToken())
                    .body(ApiResponse.withoutData(ResponseCode.SUCCESS, ResponseMessage.SUCCESS_LOGIN.getMessage()));
    }

    @GetMapping("/pass/oauth/kakao")
    public ResponseEntity<?> kakao(@ModelAttribute SocialLoginCodeReqDto socialLoginCodeReqDto) {

        socialLoginStrategyService.doSocialLogin(SocialLoginType.KAKAO, SocialLoginDtoMapper.toDomain(socialLoginCodeReqDto));

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.setLocation(URI.create(frontRedirectUri));
        return new ResponseEntity<>(resHeader, HttpStatus.FOUND);
    }

    @GetMapping("/pass/oauth/naver")
    public ResponseEntity<?> naver(@ModelAttribute SocialLoginCodeReqDto socialLoginCodeReqDto) {

        socialLoginStrategyService.doSocialLogin(SocialLoginType.NAVER, SocialLoginDtoMapper.toDomain(socialLoginCodeReqDto));

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.setLocation(URI.create(frontRedirectUri));
        return new ResponseEntity<>(resHeader, HttpStatus.FOUND);
    }

    @PostMapping("/api/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestBody LoginReqDto loginReqDto) {

        authUseCase.logout();
        return ResponseEntity.ok(
            ApiResponse.withoutData(ResponseCode.SUCCESS, ResponseMessage.SUCCESS_LOGOUT.getMessage())
        );
    }

    @PostMapping("/pass/api/register")
    public ResponseEntity<ApiResponse<Void>> post(@RequestBody LoginReqDto loginReqDto) {

        memberUseCase.register(Member.builder().name(loginReqDto.getName()).build());
        log.info("회원가입 성공! " + loginReqDto.getName());

        return ResponseEntity.ok(
            ApiResponse.withoutData(ResponseCode.SUCCESS, ResponseMessage.SUCCESS_REGISTER.getMessage())
        );
    }

    @GetMapping("/api/member")
    public ResponseEntity<ApiResponse<String>> get() {

        final String memberName = memberUseCase.getMember(authUseCase.getMemberId()).getName();
        return ResponseEntity.ok(
            ApiResponse.of(ResponseCode.SUCCESS, ResponseMessage.SUCCESS_REGISTER.getMessage(), memberName)
        );
    }

}
