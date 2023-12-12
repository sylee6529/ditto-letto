package center.unit.letter.presentation.auth;

import center.unit.letter.application.auth.LoginService;
import center.unit.letter.domain.auth.GrantType;
import center.unit.letter.presentation.auth.dto.request.KakaoAccessTokenRequest;
import center.unit.letter.presentation.auth.dto.response.AccessTokenResponse;
import center.unit.letter.shared.response.SingleCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth/kakao")
@RestController
public class KakaoAuthController {

    private final LoginService loginService;

    @GetMapping("/login")
    public SingleCommonResponse<AccessTokenResponse> login(@RequestParam("code") String code) {
        return SingleCommonResponse.ok(loginService.execute(GrantType.CODE, code));
    }

    @PostMapping("/login/token")
    public SingleCommonResponse<AccessTokenResponse> loginByKakaoOAuthToken(@RequestBody KakaoAccessTokenRequest request) {
        return SingleCommonResponse.ok(loginService.execute(GrantType.ACCESS_TOKEN, request.kakaoAccessToken()));
    }
}
