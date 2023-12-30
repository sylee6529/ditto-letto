package center.unit.letter.application.auth;

import center.unit.letter.domain.auth.GrantType;
import center.unit.letter.domain.auth.TokenService;
import center.unit.letter.domain.user.OAuthType;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.auth.OAuthFactory;
import center.unit.letter.infrastructure.auth.OAuthService;
import center.unit.letter.presentation.auth.dto.response.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final OAuthFactory oAuthFactory;
    private final TokenService tokenService;

    public AccessTokenResponse execute(OAuthType oAuthType, GrantType grantType, String key) {
        OAuthService oAuthService = oAuthFactory.getInstance(oAuthType);

        User user = oAuthService.getUserByOAuth(grantType, key);

        String accessToken = tokenService.generateAccessToken(user.getPhoneNumber());

        return new AccessTokenResponse(oAuthType, accessToken);
    }
}
