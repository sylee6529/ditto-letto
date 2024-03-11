package center.unit.letter.infrastructure.auth.apple.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import lombok.Value;

@Value
public class AppleAuthKeysResponseDTO {

    List<AppleAuthKey> keys;

    @JsonCreator
    public AppleAuthKeysResponseDTO(@JsonProperty("keys") List<AppleAuthKey> keys) {
        this.keys = keys;
    }

    public Optional<AppleAuthKey> getMatchedKey(String kid, String alg) {
        return keys
                       .stream()
                       .filter(key -> key.kid.equals(kid) && key.alg.equals(alg))
                       .findFirst();
    }

    @Value
    public static class AppleAuthKey {

        String kty;
        String kid;
        String use;
        String alg;
        String n;
        String e;

        @JsonCreator
        public AppleAuthKey(@JsonProperty("kty") String kty,
                @JsonProperty("kid") String kid,
                @JsonProperty("use") String use,
                @JsonProperty("alg") String alg,
                @JsonProperty("n") String n,
                @JsonProperty("e") String e) {
            this.kty = kty;
            this.kid = kid;
            this.use = use;
            this.alg = alg;
            this.n = n;
            this.e = e;
        }
    }
}
