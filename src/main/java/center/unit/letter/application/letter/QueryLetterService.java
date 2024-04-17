package center.unit.letter.application.letter;

import center.unit.letter.application.contact.QueryContactService;
import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.contact.exception.ContactNotFoundException;
import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.letter.exception.LetterNotFoundException;
import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.exception.UserNotFoundException;
import center.unit.letter.infrastructure.persistence.contact.ContactRepository;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.presentation.letter.dto.response.LetterResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryLetterService {

    private final LetterRepository letterRepository;
    private final ContactRepository contactRepository;

    public LetterResponse execute(User user, Long id) {
        Letter letter = getLetter(id);
        letter.isFromOrTo(user);

        Long otherUserId = letter.getFromId().equals(user.getId()) ? letter.getToId() : letter.getFromId();
        boolean isFrom = letter.getFromId().equals(user.getId());

        String otherUserPhoneNumber = null;
        try {
            otherUserPhoneNumber =  isFrom ? letter.getTo().getPhoneNumber() : letter.getFrom().getPhoneNumber();
        } catch (EntityNotFoundException exception) {
        }

        Contact contact = contactRepository.findByUserAndPhoneNumber(user, otherUserPhoneNumber);

        return new LetterResponse(
                letter,
                otherUserPhoneNumber,
                contact,
                letterRepository.countByFromAndTo(user.getId(), otherUserId),
                letterRepository.countByFromAndTo(otherUserId, user.getId())
        );
    }

    private Letter getLetter(Long id) {
        return letterRepository.findById(id)
                .orElseThrow(LetterNotFoundException::new);
    }
}
