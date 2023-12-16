package center.unit.letter.infrastructure.auth.kakao;

import center.unit.letter.application.auth.dto.OAuthUserInfo;
import center.unit.letter.domain.auth.GrantType;
import center.unit.letter.domain.auth.OAuthType;
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

    @Override
    @Transactional(readOnly = true)
    public User getUserByOAuth(GrantType grantType, String key) {
        String accessToken = getAccessToken(grantType, key);

        KakaoUserInfoResponse kakaoUserInfo = getKakaoUserInfoByAccessToken(accessToken);

        // TODO: 12/15/23 OAuthType 을 저장하고 두 column 을 기준으로 저장/확인 하도록 변경 필요
        return userRepository.findByKakaoUserId(kakaoUserInfo.id())
                       .orElseThrow(() -> new BaseException(GlobalErrorCode.UNAUTHORIZED));
    }

    private String getKakaoAccessTokenByCode(String accessCode) {
        // KAKAO 토큰 요청
        KakaoAuthTokenResponse kakaoAuthTokenResponse = kakaoAuthClient.requestAuthToken(
                GRANT_TYPE_AUTHORIZATION_CODE,
                KAKAO_CLIENT_KEY,
                KAKAO_REDIRECT_URI,
                accessCode
        );

        // TODO: 2023/12/15 인증 실패 Exception 추가
        if (kakaoAuthTokenResponse == null) {
            throw new RuntimeException("KAKAO 인증에 실패했습니다.");
        }

        return kakaoAuthTokenResponse.accessToken();
    }

    private KakaoUserInfoResponse getKakaoUserInfoByAccessToken(String accessToken) {
        String bearerToken = "Bearer " + accessToken;
        return kakaoApiClient.requestUserInfo(bearerToken);
    }

    @Override
    @Transactional(readOnly = true)
    public OAuthUserInfo getOAuthUserInfo(GrantType grantType, String key) {
        String accessToken = getAccessToken(grantType, key);

        KakaoUserInfoResponse kakaoUserInfo = getKakaoUserInfoByAccessToken(accessToken);

        return new OAuthUserInfo(
                OAuthType.KAKAO,
                String.valueOf(kakaoUserInfo.id()),
                kakaoUserInfo.kakaoAccount().profile().nickname());
    }

    private String getAccessToken(GrantType grantType, String key) {
        return switch (grantType) {
            case CODE -> getKakaoAccessTokenByCode(key);
            case ACCESS_TOKEN -> key;
        };
    }
}
