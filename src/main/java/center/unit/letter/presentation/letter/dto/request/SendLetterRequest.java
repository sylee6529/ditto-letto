package center.unit.letter.presentation.letter.dto.request;

import center.unit.letter.domain.letter.type.LetterType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SendLetterRequest {

    @NotBlank
    @Size(max = 144)
    private String text;

    // TODO: 추후 선택으로 변경할 것
//    @NotNull
//    private MediumType mediumType;

    @NotNull
    private LetterType type;

    @NotBlank
    private String targetPhoneNumber;
}
