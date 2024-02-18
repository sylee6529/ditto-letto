package center.unit.letter.infrastructure.auth;

import center.unit.letter.domain.user.OAuthType;
import center.unit.letter.infrastructure.auth.apple.AppleOAuthService;
import center.unit.letter.infrastructure.auth.kakao.KakaoOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OAuthFactory {

    private final KakaoOAuthService kakaoOAuthService;
    private final AppleOAuthService appleOAuthService;

    public OAuthService getInstance(OAuthType oAuthType) {
        return switch (oAuthType) {
            case KAKAO -> kakaoOAuthService;
            case APPLE -> appleOAuthService;
        };
    }
}
