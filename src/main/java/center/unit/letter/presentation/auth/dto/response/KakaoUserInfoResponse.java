package center.unit.letter.presentation.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: 카카오 유저 정보가 필요하다면 추가하여 가져올 것
public record KakaoUserInfoResponse(String id, KakaoAccount kakaoAccount) {

    @JsonCreator

    public KakaoUserInfoResponse(
            @JsonProperty("id") String id,
            @JsonProperty("kakao_account") KakaoAccount kakaoAccount) {
        this.id = id;
        this.kakaoAccount = kakaoAccount;
    }

    public record KakaoAccount(KakaoProfile profile) {

        @JsonCreator
        public KakaoAccount(@JsonProperty("profile") KakaoProfile profile) {
            this.profile = profile;
        }
    }

    public record KakaoProfile(String nickname) {

        @JsonCreator
        public KakaoProfile(@JsonProperty("nickname") String nickname) {
            this.nickname = nickname;
        }
    }
}
