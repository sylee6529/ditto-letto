package center.unit.letter.application.contact;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.contact.service.ContactFacade;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.contact.dto.request.UpdateContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateContactService {

    private final ContactFacade contactFacade;

    @Transactional
    public void execute(User user, Long id, UpdateContactRequest request) {
        Contact contact = contactFacade.getContact(id);
        contact.ownerIs(user);

        contact.update(
                request.getName(),
                request.getCharacter(),
                request.getPhoneNumber()
        );
    }
}
