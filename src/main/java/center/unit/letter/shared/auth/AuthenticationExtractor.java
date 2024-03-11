package center.unit.letter.shared.auth;

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
            throw new IllegalArgumentException("token이 없습니다");
        }

        if (!authorizationHeader.startsWith(jwtProperties.getPrefix())) {
            throw new IllegalArgumentException("토큰의 접두사가 올바르지 않습니다");
        }

        return authorizationHeader.replace(jwtProperties.getPrefix(), "").trim();
    }
}