package center.unit.letter.application.user;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.user.PhoneNumberUpdateLog;
import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.WithdrawLog;
import center.unit.letter.infrastructure.persistence.contact.ContactRepository;
import center.unit.letter.infrastructure.persistence.user.PhoneNumberUpdateLogRepository;
import center.unit.letter.infrastructure.persistence.user.UserRepository;
import center.unit.letter.infrastructure.persistence.user.WithdrawLogRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WithdrawUserService {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final PhoneNumberUpdateLogRepository phoneNumberUpdateLogRepository;
    private final WithdrawLogRepository withdrawLogRepository;

    public void withdrawUser(User user, String reason) {
        List<Contact> contacts = contactRepository.findByUser(user);
        List<PhoneNumberUpdateLog> logs = phoneNumberUpdateLogRepository.findAllByUser(user);

        WithdrawLog withdrawLog = new WithdrawLog(user, reason);

        withdrawLogRepository.save(withdrawLog);

        contactRepository.deleteAll(contacts);
        // phone number update log를 남기고자 한다면, letter와 같은 방법으로 외래키 끊을 것
        phoneNumberUpdateLogRepository.deleteAll(logs);
        userRepository.delete(user);
    }
}
