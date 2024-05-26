package center.unit.letter.presentation.auth.dto.request;

import center.unit.letter.domain.auth.GrantType;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class SignUpRequest {

    @NotBlank
    GrantType grantType;

    @NotBlank
    String key;

    // 2024/05/26 서버에서 번호를 임의로 부여하도록 임시 변경
    /*@NotBlank
    @Size(min = 11, max = 11)
    String phoneNumber;*/

    Double longitude;

    Double latitude;
}
