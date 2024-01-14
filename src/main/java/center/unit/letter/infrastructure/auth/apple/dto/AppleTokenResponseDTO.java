package center.unit.letter.infrastructure.auth.apple.dto;

import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Value
public class AppleTokenResponseDTO {

    String tokenType;
    String accessToken;
    long expiresIn;
    String refreshToken;
    long refreshTokenExpiresIn;
    String idToken;
}
