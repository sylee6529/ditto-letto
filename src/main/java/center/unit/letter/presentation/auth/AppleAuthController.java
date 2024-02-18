package center.unit.letter.presentation.auth;

import center.unit.letter.application.auth.LoginService;
import center.unit.letter.application.auth.SignUpService;
import center.unit.letter.domain.auth.GrantType;
import center.unit.letter.domain.user.OAuthType;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.auth.dto.request.LoginByOAuthAccessTokenRequest;
import center.unit.letter.presentation.auth.dto.request.SignUpRequest;
import center.unit.letter.presentation.auth.dto.response.AccessTokenResponse;
import center.unit.letter.presentation.auth.dto.response.SignUpResponse;
import center.unit.letter.shared.response.CommonResponse;
import center.unit.letter.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth/apple")
@RestController
public class AppleAuthController {

    private final LoginService loginService;
    private final SignUpService signUpService;

    @PostMapping("/sign-up")
    public SingleCommonResponse<SignUpResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        User user = signUpService.execute(
                OAuthType.APPLE,
                request.getGrantType(),
                request.getKey(),
                request.getPhoneNumber(),
                request.getLongitude(),
                request.getLatitude()
        );

        return CommonResponse.ok(SignUpResponse.of(user));
    }

    @GetMapping("/login")
    public SingleCommonResponse<AccessTokenResponse> login(@RequestParam("code") String code) {
        return SingleCommonResponse.ok(loginService.execute(OAuthType.APPLE, GrantType.CODE, code));
    }

    @PostMapping("/login/token")
    public SingleCommonResponse<AccessTokenResponse> loginByKakaoOAuthToken(@RequestBody LoginByOAuthAccessTokenRequest request) {
        return SingleCommonResponse.ok(loginService.execute(OAuthType.APPLE, GrantType.ACCESS_TOKEN, request.accessToken()));
    }
}
