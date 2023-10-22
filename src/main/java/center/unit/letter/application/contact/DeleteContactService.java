package center.unit.letter.application.contact;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.contact.service.ContactFacade;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.persistence.contact.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteContactService {

    private final ContactFacade contactFacade;
    private final ContactRepository contactRepository;

    @Transactional
    public void execute(User user, Long id) {
        Contact contact = contactFacade.getContact(id);
        contact.ownerIs(user);

        contactRepository.delete(contact);
    }
}
