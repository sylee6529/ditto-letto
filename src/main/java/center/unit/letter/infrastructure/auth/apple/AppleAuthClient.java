package center.unit.letter.infrastructure.auth.apple;

import center.unit.letter.infrastructure.auth.apple.dto.AppleAuthKeysResponseDTO;
import center.unit.letter.infrastructure.auth.apple.dto.AppleTokenResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name = "appleAuth", url = "https://appleid.apple.com")
public interface AppleAuthClient {

    @PostMapping(value = "/auth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AppleTokenResponseDTO requestIdToken(
            @RequestPart(value = "grant_type") String grantType,
            @RequestPart(value = "redirect_uri") String redirectUri,
            @RequestPart(value = "client_id") String clientId,
            @RequestPart(value = "client_secret") String clientSecret,
            @RequestPart(value = "code") String code
    );

    @PostMapping(value = "/auth/keys")
    AppleAuthKeysResponseDTO getAppleAuthKeys();
}
