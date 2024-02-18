package center.unit.letter.application.user;

import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.user.dto.request.UpdateUserLocationRequest;
import center.unit.letter.presentation.user.dto.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateUserService {

    @Transactional
    public void execute(User user, UpdateUserRequest request) {
        user.update(request.getName(), request.getPhoneNumber());
    }

    @Transactional
    public void updateLocation(User user, UpdateUserLocationRequest request) {
        user.updateLocation(request.getLongitude(), request.getLatitude());
    }
}
