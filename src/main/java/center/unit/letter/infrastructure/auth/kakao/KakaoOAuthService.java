package center.unit.letter.infrastructure.auth.kakao;

import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.auth.OAuthService;
import center.unit.letter.infrastructure.persistence.user.UserRepository;
import center.unit.letter.presentation.auth.dto.response.KakaoAuthTokenResponse;
import center.unit.letter.presentation.auth.dto.response.KakaoUserInfoResponse;
import center.unit.letter.shared.error.BaseException;
import center.unit.letter.shared.error.exception.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class KakaoOAuthService implements OAuthService {

    private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";

    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoApiClient kakaoApiClient;
    private final UserRepository userRepository;

    @Value("${kakao.api.client-key}")
    private String KAKAO_CLIENT_KEY;

    @Value("${kakao.api.redirect-uri}")
    private String KAKAO_REDIRECT_URI;

    @Transactional
    @Override
    public User requestJwtByOAuthAccessCode(String accessCode) {
        // KAKAO 토큰 요청
        KakaoAuthTokenResponse kakaoAuthTokenResponse = kakaoAuthClient.requestAuthToken(
                GRANT_TYPE_AUTHORIZATION_CODE,
                KAKAO_CLIENT_KEY,
                KAKAO_REDIRECT_URI,
                accessCode
        );

        // TODO: 인증 실패 Exception
        if (kakaoAuthTokenResponse == null) {
            throw new RuntimeException("KAKAO 인증에 실패했습니다.");
        }

        return requestJwtByOAuthAccessToken(kakaoAuthTokenResponse.accessToken());
    }

    @Override
    @Transactional
    public User requestJwtByOAuthAccessToken(String accessToken) {
        KakaoUserInfoResponse kakaoUserInfo = getKakaoUserInfo(accessToken);

        return userRepository.findByKakaoUserId(kakaoUserInfo.id())
                       .orElseThrow(() -> new BaseException(GlobalErrorCode.UNAUTHORIZED));
    }

    private KakaoUserInfoResponse getKakaoUserInfo(String accessToken) {
        String bearerToken = "Bearer " + accessToken;
        return kakaoApiClient.requestUserInfo(bearerToken);
    }
}
