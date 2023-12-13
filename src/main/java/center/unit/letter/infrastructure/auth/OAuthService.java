package center.unit.letter.infrastructure.auth;

import center.unit.letter.domain.user.User;

public interface OAuthService {

    User requestJwtByOAuthAccessCode(String accessCode);

    User requestJwtByOAuthAccessToken(String accessToken);
}
