package center.unit.letter.infrastructure.kakao;

import center.unit.letter.domain.auth.TokenService;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.persistence.user.UserRepository;
import center.unit.letter.presentation.auth.dto.response.AccessTokenResponse;
import center.unit.letter.presentation.auth.dto.response.KakaoAuthTokenResponse;
import center.unit.letter.presentation.auth.dto.response.KakaoUserInfoResponse;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {

    private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";

    private final TokenService tokenService;
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoApiClient kakaoApiClient;
    private final UserRepository userRepository;

    @Value("${kakao.api.client-key}")
    private String KAKAO_CLIENT_KEY;

    @Value("${kakao.api.redirect-uri}")
    private String KAKAO_REDIRECT_URI;

    public AccessTokenResponse requestToken(String code) {
        // KAKAO 토큰 요청
        KakaoAuthTokenResponse kakaoAuthTokenResponse = kakaoAuthClient.requestAuthToken(
            GRANT_TYPE_AUTHORIZATION_CODE,
            KAKAO_CLIENT_KEY,
            KAKAO_REDIRECT_URI,
            code
        );

        // TODO: 인증 실패 Exception
        if (kakaoAuthTokenResponse == null) {
            throw new RuntimeException("KAKAO 인증에 실패했습니다.");
        }

        // KAKAO 사용자 정보 조회
        KakaoUserInfoResponse kakaoUserInfo = getKakaoUserInfo(kakaoAuthTokenResponse.accessToken());

        // 사용자 정보 검증 및 최초 로그인 시 사용자 정보 등록
        User user = userRepository.findByKakaoUserId(kakaoUserInfo.id())
            .orElseGet(() -> {
                User newUser = new User(
                    kakaoUserInfo.kakaoAccount().profile().nickname(),
                    generatePhoneNumber(),
                    kakaoUserInfo.id()
                );
                return userRepository.save(newUser);
            });

        // AccessToken 반환
        return new AccessTokenResponse(tokenService.generateAccessToken(user.getPhoneNumber()));
    }

    private KakaoUserInfoResponse getKakaoUserInfo(String accessToken) {
        String bearerToken = "Bearer " + accessToken;
        return kakaoApiClient.requestUserInfo(bearerToken);
    }

    private String generatePhoneNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("010");

        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
