package center.unit.letter.application.contact;

import center.unit.letter.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteContactService {
    public void execute(User user, Long id) {
    }
}
