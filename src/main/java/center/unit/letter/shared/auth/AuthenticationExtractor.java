package center.unit.letter.shared.auth;

import center.unit.letter.domain.auth.exception.InvalidPrefixException;
import center.unit.letter.domain.auth.exception.TokenNotFoundException;
import center.unit.letter.shared.config.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

@RequiredArgsConstructor
@Component
public class AuthenticationExtractor {

    private final JwtProperties jwtProperties;

    public String extract(NativeWebRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            throw new TokenNotFoundException();
        }

        if (!authorizationHeader.startsWith(jwtProperties.getPrefix())) {
            throw new InvalidPrefixException();
        }

        return authorizationHeader.replace(jwtProperties.getPrefix(), "").trim();
    }
}