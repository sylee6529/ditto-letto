package center.unit.letter.application.contact;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.contact.service.ContactFacade;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.contact.dto.response.ContactDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryContactService {

    private final ContactFacade contactFacade;

    public ContactDetailResponse execute(User user, Long id) {
        Contact contact = contactFacade.getContact(id);
        contact.ownerIs(user);

        return new ContactDetailResponse(contact);
    }
}
