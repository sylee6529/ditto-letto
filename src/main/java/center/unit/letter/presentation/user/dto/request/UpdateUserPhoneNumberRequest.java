package center.unit.letter.presentation.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPhoneNumberRequest {
    @NotBlank
    @Size(min = 11, max = 11)
    private String phoneNumber;
}
