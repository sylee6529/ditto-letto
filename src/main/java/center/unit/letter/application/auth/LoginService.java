package center.unit.letter.application.auth;

import center.unit.letter.domain.auth.GrantType;
import center.unit.letter.domain.auth.TokenService;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.kakao.KakaoAuthService;
import center.unit.letter.presentation.auth.dto.response.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final KakaoAuthService kakaoAuthService;
    private final TokenService tokenService;

    public AccessTokenResponse execute(GrantType grantType, String key) {
        User user = switch (grantType) {
            case CODE -> kakaoAuthService.requestTokenByAccessCode(key);
            case ACCESS_TOKEN -> kakaoAuthService.requestTokenByAccessToken(key);
        };

        String accessToken = tokenService.generateAccessToken(user.getPhoneNumber());

        return new AccessTokenResponse(accessToken);
    }
}
