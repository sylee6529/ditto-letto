package center.unit.letter.domain.contact.service;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.contact.exception.ContactNotFoundException;
import center.unit.letter.infrastructure.persistence.contact.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContactFacade {

    private final ContactRepository contactRepository;

    public Contact getContact(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(ContactNotFoundException::new);
    }
}
