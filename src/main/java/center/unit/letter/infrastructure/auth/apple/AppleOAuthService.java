package center.unit.letter.infrastructure.auth.apple;

import center.unit.letter.application.auth.dto.OAuthUserInfo;
import center.unit.letter.domain.auth.GrantType;
import center.unit.letter.domain.user.OAuthType;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.auth.OAuthService;
import center.unit.letter.infrastructure.auth.apple.dto.AppleAuthKeysResponseDTO;
import center.unit.letter.infrastructure.auth.apple.dto.AppleTokenResponseDTO;
import center.unit.letter.infrastructure.persistence.user.UserRepository;
import center.unit.letter.shared.config.properties.AppleProperties;
import center.unit.letter.shared.error.BaseException;
import center.unit.letter.shared.error.exception.GlobalErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.Charsets;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppleOAuthService implements OAuthService {

    private final AppleAuthClient appleAuthClient;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final AppleProperties properties;

    /*
    GrantType.ACCESS_TOKEN 은 모바일을 통해서 들어오고 이가 ID TOKEN 이라고 가정함!
     */
    @Override
    public User getUserByOAuth(GrantType grantType, String key) {
        String idToken = switch (grantType) {
            case CODE -> getIdTokenByOAuthCode(key);
            case ACCESS_TOKEN -> key;
        };

        Claims claims = validateIdToken(idToken);
        String appleId = claims.getSubject();

        return userRepository.findByOauthTypeAndOauthId(OAuthType.APPLE, appleId)
                       .orElseThrow(() -> new BaseException(GlobalErrorCode.UNAUTHORIZED));
    }

    private String getIdTokenByOAuthCode(String code) {
        String clientSecretToken = createClientSecretToken();

        AppleTokenResponseDTO tokenResponse = appleAuthClient.requestIdToken(
                "authorization_code",
                properties.getRedirectUri(),
                properties.getClientId(),
                clientSecretToken,
                code
        );

        return tokenResponse.idToken();
    }

    private String createClientSecretToken() {
        PrivateKey privateKey = getAppleIdPrivateKey();

        // JWT 클레임 설정
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);
        Date expiration = new Date(currentTimeMillis + 3600000); // 1 hour

        // JWT 생성
        return Jwts.builder()
                       .header()
                       .keyId(properties.getClientSecret())
                       .setAlgorithm(SignatureAlgorithm.ES256.toString())
                       .and()
                       .issuer(properties.getTeamId())
                       .issuedAt(now)
                       .expiration(expiration)
                       .audience()
                       .add("https://appleid.apple.com")
                       .and()
                       .subject(properties.getClientId())
                       .signWith(privateKey)
                       .compact();
    }

    private PrivateKey getAppleIdPrivateKey() {
        InputStream privateKey;

        try {
            privateKey = new ClassPathResource("ApplePrivateKey.p8").getInputStream();
        } catch (Exception e) {
            throw new BaseException(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }

        String result = new BufferedReader(new InputStreamReader(privateKey)).lines().collect(Collectors.joining("\n"));

        String key = result
                             .replace("-----BEGIN PRIVATE KEY-----\n", "")
                             .replace("-----END PRIVATE KEY-----", "");

        byte[] encoded = Base64.decodeBase64(key);

        try {
            PrivateKey generatedPrivateKey;
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            generatedPrivateKey = keyFactory.generatePrivate(keySpec);

            return generatedPrivateKey;
        } catch (Exception e) {
            throw new BaseException(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private Claims validateIdToken(String idToken) {
        AppleAuthKeysResponseDTO appleAuthKeys = appleAuthClient.getAppleAuthKeys();

        // idToken 의 header 부분 decode -> base64 to UTF-8
        // 해서 나오는 값의 kid, alg 가지고 keys 에서 일치하는 키 찾고
        // (RSA 방식이니) n, e 사용해서 공개키를 만들어서 복호화 하는 것으로 키가 올바르다는 것을 검증함
        // 검증된 키의 payload 부분에서 subject, email 추출
        try {
            String headerOfIdentityToken = idToken.substring(0, idToken.indexOf("."));
            Decoder urlDecoder = java.util.Base64.getUrlDecoder();

            Map<String, String> header = objectMapper.readValue(new String(urlDecoder.decode(headerOfIdentityToken), Charsets.UTF_8), Map.class);
            AppleAuthKeysResponseDTO.AppleAuthKey key = appleAuthKeys.getMatchedKey(header.get("kid"), header.get("alg"))
                                                                .orElseThrow(() -> new BaseException(GlobalErrorCode.INTERNAL_SERVER_ERROR));

            byte[] nBytes = urlDecoder.decode(key.getN());
            byte[] eBytes = urlDecoder.decode(key.getE());

            BigInteger n = new BigInteger(1, nBytes);
            BigInteger e = new BigInteger(1, eBytes);

            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
            KeyFactory keyFactory = KeyFactory.getInstance(key.getKty());
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            JwtParser jwtParser = Jwts.parser()
                                          .verifyWith(publicKey)
                                          .build();
            return jwtParser
                           .parseSignedClaims(idToken)
                           .getPayload();
        } catch (Exception e) {
            throw new BaseException(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public OAuthUserInfo getOAuthUserInfo(GrantType grantType, String key) {
        String idToken = switch (grantType) {
            case CODE -> getIdTokenByOAuthCode(key);
            case ACCESS_TOKEN -> key;
        };

        Claims claims = validateIdToken(idToken);
        String appleId = claims.getSubject();
        // Apple IdToken 에는 이름이 들어있지 않아 email 로 대체!
        String email = claims.get("email", String.class);

        return new OAuthUserInfo(OAuthType.APPLE, appleId, email);
    }
}
