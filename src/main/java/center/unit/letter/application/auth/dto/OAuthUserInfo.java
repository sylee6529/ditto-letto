package center.unit.letter.application.auth.dto;

import center.unit.letter.domain.user.OAuthType;
import lombok.Value;

@Value
public class OAuthUserInfo {

    OAuthType oAuthType;
    String oauthId;
    String name;
}
