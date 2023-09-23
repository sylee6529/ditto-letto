package center.unit.letter.application.user;

import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.user.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class QueryUserService {

    public UserResponse execute(User user) {
        return new UserResponse(user);
    }
}
