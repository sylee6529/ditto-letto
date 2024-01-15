package center.unit.letter.infrastructure.persistence.contact;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    boolean existsByUserAndPhoneNumber(User user, String phoneNumber);

    List<Contact> findByUser(User user);

    Contact findByUserAndPhoneNumber(User user, String phoneNumber);
}
