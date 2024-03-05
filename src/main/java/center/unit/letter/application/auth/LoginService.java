package center.unit.letter.application.auth;

import center.unit.letter.domain.auth.GrantType;
import center.unit.letter.domain.auth.TokenService;
import center.unit.letter.domain.user.OAuthType;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.auth.OAuthFactory;
import center.unit.letter.infrastructure.auth.OAuthService;
import center.unit.letter.infrastructure.auth.kakao.KakaoOAuthService;
import center.unit.letter.presentation.auth.dto.response.AccessTokenResponse;
import center.unit.letter.presentation.auth.dto.response.KakaoAuthTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final OAuthFactory oAuthFactory;
    private final TokenService tokenService;

    public AccessTokenResponse execute(OAuthType oAuthType, GrantType grantType, String key) {
        OAuthService oAuthService = oAuthFactory.getInstance(oAuthType);

        User user = oAuthService.getUserByOAuth(grantType, key);

        String accessToken = tokenService.generateAccessToken(user.getPhoneNumber());

        return new AccessTokenResponse(oAuthType, accessToken);
    }

    public KakaoAuthTokenResponse getKakaoAccessToken(OAuthType oAuthType, String authCode) {
        if (OAuthType.KAKAO.equals(oAuthType)) {
            OAuthService oAuthService = oAuthFactory.getInstance(OAuthType.KAKAO);
            KakaoOAuthService kakaoOAuthService = (KakaoOAuthService) oAuthService;

            return kakaoOAuthService.getKakaoAccessToken(authCode);
        }
        return null;
    }
}
