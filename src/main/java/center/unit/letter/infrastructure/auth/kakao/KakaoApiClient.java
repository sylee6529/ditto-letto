package center.unit.letter.infrastructure.auth.kakao;

import center.unit.letter.presentation.auth.dto.response.KakaoUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoApi", url = "https://kapi.kakao.com")
public interface KakaoApiClient {

    @PostMapping(
            value = "/v2/user/me",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoUserInfoResponse requestUserInfo(@RequestHeader("Authorization") String bearerToken);
}
