package center.unit.letter.application.contact;

import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.persistence.contact.ContactRepository;
import center.unit.letter.presentation.contact.dto.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryMyContactService {

    private final ContactRepository contactRepository;
    public List<ContactResponse> execute(User user) {
        return contactRepository.findByUser(user)
                .stream()
                .map(ContactResponse::new)
                .toList();
    }
}
