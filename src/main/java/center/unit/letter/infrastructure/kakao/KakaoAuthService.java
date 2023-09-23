package center.unit.letter.infrastructure.kakao;

import center.unit.letter.presentation.auth.dto.response.KakaoAuthTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {

    private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";

    private final KakaoAuthClient kakaoAuthClient;

    @Value("${kakao.api.client-key}")
    private String KAKAO_CLIENT_KEY;

    @Value("${kakao.api.redirect-uri}")
    private String KAKAO_REDIRECT_URI;

    public KakaoAuthTokenResponse requestToken(String code) {
        return kakaoAuthClient.requestAuthToken(
            GRANT_TYPE_AUTHORIZATION_CODE,
            KAKAO_CLIENT_KEY,
            KAKAO_REDIRECT_URI,
            code
        );
    }
}
