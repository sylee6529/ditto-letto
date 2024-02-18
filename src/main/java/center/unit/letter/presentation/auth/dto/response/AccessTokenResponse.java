package center.unit.letter.presentation.auth.dto.response;

import center.unit.letter.domain.user.OAuthType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AccessTokenResponse(OAuthType oAuthType, String accessToken) {

    @JsonCreator
    public AccessTokenResponse(
            @JsonProperty("oAuthType") OAuthType oAuthType,
            @JsonProperty("accessToken") String accessToken
    ) {
        this.oAuthType = oAuthType;
        this.accessToken = accessToken;
    }
}
