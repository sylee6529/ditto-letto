package center.unit.letter.presentation.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginByOAuthAccessTokenRequest(String accessToken) {

    @JsonCreator
    public LoginByOAuthAccessTokenRequest(@JsonProperty("accessToken") String accessToken) {
        this.accessToken = accessToken;
    }
}
