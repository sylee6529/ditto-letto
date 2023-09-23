package center.unit.letter.presentation.user.dto.response;

import center.unit.letter.domain.user.User;
import lombok.Value;

@Value
public class UserResponse {

    String phoneNumber;
    String name;

    public UserResponse(User user) {
        this.phoneNumber = user.getPhoneNumber();
        this.name = user.getName();
    }
}
