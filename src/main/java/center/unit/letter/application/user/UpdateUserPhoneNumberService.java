package center.unit.letter.application.user;

import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.user.dto.request.UpdateUserPhoneNumberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateUserPhoneNumberService {

    @Transactional
    public void execute(User user, UpdateUserPhoneNumberRequest request) {
        user.updatePhoneNumber(request.getPhoneNumber());
    }
}
