package center.unit.letter.infrastructure.auth;

import center.unit.letter.application.auth.dto.OAuthUserInfo;
import center.unit.letter.domain.auth.GrantType;
import center.unit.letter.domain.user.User;

public interface OAuthService {

    User getUserByOAuth(GrantType grantType, String key);

    OAuthUserInfo getOAuthUserInfo(GrantType grantType, String key);
}
