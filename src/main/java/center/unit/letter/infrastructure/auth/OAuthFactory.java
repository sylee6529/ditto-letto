package center.unit.letter.infrastructure.auth;

import center.unit.letter.domain.auth.OAuthType;
import center.unit.letter.infrastructure.auth.kakao.KakaoOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OAuthFactory {

    private final KakaoOAuthService kakaoOAuthService;

    public OAuthService getInstance(OAuthType oAuthType) {
        return switch (oAuthType) {
            case KAKAO -> kakaoOAuthService;
            case APPLE -> null;
        };
    }
}
