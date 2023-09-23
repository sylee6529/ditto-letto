package center.unit.letter.presentation.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoAccessTokenRequest(String kakaoAccessToken) {

    @JsonCreator
    public KakaoAccessTokenRequest(@JsonProperty("kakaoAccessToken") String kakaoAccessToken) {
        this.kakaoAccessToken = kakaoAccessToken;
    }
}
