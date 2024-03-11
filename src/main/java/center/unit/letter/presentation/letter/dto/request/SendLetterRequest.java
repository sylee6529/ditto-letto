package center.unit.letter.presentation.letter.dto.request;

import center.unit.letter.domain.letter.type.LetterType;
import center.unit.letter.domain.letter.type.MediumType;
import center.unit.letter.domain.user.User;
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

    @NotNull
    private LetterType type;

    @NotNull
    private String toPhoneNumber;

    private Double longitude;

    private Double latitude;
}
