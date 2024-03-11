package center.unit.letter.application.auth;

import center.unit.letter.application.auth.dto.OAuthUserInfo;
import center.unit.letter.domain.auth.GrantType;
import center.unit.letter.domain.user.OAuthType;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.auth.OAuthFactory;
import center.unit.letter.infrastructure.auth.OAuthService;
import center.unit.letter.infrastructure.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignUpService {

    private final OAuthFactory oAuthFactory;
    private final UserRepository userRepository;

    @Transactional
    public User execute(OAuthType oAuthType, GrantType grantType, String oauthKey, String phoneNumber, Double longitude, Double latitude) {
        OAuthService oAuthService = oAuthFactory.getInstance(oAuthType);

        OAuthUserInfo oAuthUserInfo = oAuthService.getOAuthUserInfo(grantType, oauthKey);
        User user = new User(
                oAuthUserInfo.getName(),
                phoneNumber,
                oAuthType,
                oAuthUserInfo.getOauthId(),
                longitude,
                latitude
        );

        return userRepository.save(user);
    }
}
