package center.unit.letter.application.user;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.WithdrawLog;
import center.unit.letter.infrastructure.persistence.contact.ContactRepository;
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
    private final WithdrawLogRepository withdrawLogRepository;

    public void withdrawUser(User user, String reason) {
        List<Contact> contacts = contactRepository.findByUser(user);

        WithdrawLog withdrawLog = new WithdrawLog(user, reason);

        withdrawLogRepository.save(withdrawLog);

        contactRepository.deleteAll(contacts);
        userRepository.delete(user);
    }
}
