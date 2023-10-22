package center.unit.letter.application.contact;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.contact.exception.ContactAlreadySavedException;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.persistence.contact.ContactRepository;
import center.unit.letter.presentation.contact.dto.request.ContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveContactService {

    private final ContactRepository contactRepository;

    public void execute(User user, ContactRequest request) {
        validation(user, request.getPhoneNumber());

        Contact contact = new Contact(
                request.getPhoneNumber(),
                request.getName(),
                request.getCharacter(),
                user
        );

        contactRepository.save(contact);
    }

    private void validation(User user, String phoneNumber) {
        if (contactRepository.existsByUserAndPhoneNumber(user, phoneNumber)) {
            throw new ContactAlreadySavedException();
        }
    }
}
