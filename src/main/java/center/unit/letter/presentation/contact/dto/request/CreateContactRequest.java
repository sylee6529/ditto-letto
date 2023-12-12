package center.unit.letter.presentation.contact.dto.request;

import center.unit.letter.domain.contact.type.Character;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactRequest {

    @NotBlank
    private String name;

    @NotNull
    private Character character;

    @NotBlank
    @Size(min = 11, max = 11)
    private String phoneNumber;
}
