package center.unit.letter.application.user;

import center.unit.letter.domain.user.PhoneNumberUpdateLog;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.persistence.user.PhoneNumberUpdateLogRepository;
import center.unit.letter.presentation.user.dto.request.UpdateUserPhoneNumberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateUserPhoneNumberService {

    private final PhoneNumberUpdateLogRepository phoneNumberUpdateLogRepository;

    @Transactional
    public void execute(User user, UpdateUserPhoneNumberRequest request) {
        PhoneNumberUpdateLog updateLog = new PhoneNumberUpdateLog(
                        user.getPhoneNumber(),
                        request.getPhoneNumber(),
                        user
                );

        phoneNumberUpdateLogRepository.save(updateLog);

        user.updatePhoneNumber(request.getPhoneNumber());
    }
}
