package center.unit.letter.presentation.auth.dto.request;

import center.unit.letter.domain.auth.GrantType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class SignUpRequest {

    @NotBlank
    GrantType grantType;

    @NotBlank
    String key;

    @NotBlank
    @Size(min = 11, max = 11)
    String phoneNumber;

    Double longitude;

    Double latitude;
}
