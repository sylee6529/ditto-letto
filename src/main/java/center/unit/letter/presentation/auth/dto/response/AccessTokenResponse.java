package center.unit.letter.presentation.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AccessTokenResponse(String accessToken) {

    @JsonCreator
    public AccessTokenResponse(@JsonProperty("accessToken") String accessToken) {
        this.accessToken = accessToken;
    }
}
