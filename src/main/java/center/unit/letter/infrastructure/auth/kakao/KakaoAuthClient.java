package center.unit.letter.infrastructure.auth.kakao;

import center.unit.letter.presentation.auth.dto.response.KakaoAuthTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name = "kakaoAuth", url = "https://kauth.kakao.com")
public interface KakaoAuthClient {

    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoAuthTokenResponse requestAuthToken(
            @RequestPart(value = "grant_type") String grantType,
            @RequestPart(value = "client_id") String clientId,
            @RequestPart(value = "redirect_uri") String redirectUri,
            @RequestPart(value = "code") String code
    );
}
