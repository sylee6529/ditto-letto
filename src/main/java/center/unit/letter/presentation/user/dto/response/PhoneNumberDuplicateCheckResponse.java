package center.unit.letter.presentation.user.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class PhoneNumberDuplicateCheckResponse {
    @JsonProperty("isDuplicated")
    boolean duplicated;
}
