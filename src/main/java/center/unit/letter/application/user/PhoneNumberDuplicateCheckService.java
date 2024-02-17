package center.unit.letter.application.user;

import center.unit.letter.infrastructure.persistence.user.UserRepository;
import center.unit.letter.presentation.user.dto.response.PhoneNumberDuplicateCheckResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PhoneNumberDuplicateCheckService {
    private final UserRepository userRepository;

    public PhoneNumberDuplicateCheckResponse execute(String number) {
        return new PhoneNumberDuplicateCheckResponse(userRepository.findByPhoneNumber(number).isPresent());
    }
}
