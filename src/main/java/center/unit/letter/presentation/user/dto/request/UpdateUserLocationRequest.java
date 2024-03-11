package center.unit.letter.presentation.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class UpdateUserLocationRequest {
    private double longitude;
    private double latitude;
}
