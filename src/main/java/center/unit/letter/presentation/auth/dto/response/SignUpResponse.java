package center.unit.letter.presentation.auth.dto.response;

import center.unit.letter.domain.user.OAuthType;
import center.unit.letter.domain.user.User;
import lombok.Value;

@Value
public class SignUpResponse {

    long id;
    String name;
    String phoneNumber;
    String oauthId;
    OAuthType oAuthType;

    private SignUpResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.oauthId = user.getOauthId();
        this.oAuthType = user.getOauthType();
    }

    public static SignUpResponse of(User user) {
        return new SignUpResponse(user);
    }
}
