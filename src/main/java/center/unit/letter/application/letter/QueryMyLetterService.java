package center.unit.letter.application.letter;

import center.unit.letter.application.contact.QueryMyContactService;
import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.letter.type.MyLetterType;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.persistence.contact.ContactRepository;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.presentation.letter.dto.MyLetterDto;
import center.unit.letter.presentation.letter.dto.response.MyLetterListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryMyLetterService {
    private final static int MAX_PREVIEW_TEXT_LENGTH = 25;
    private final LetterRepository letterRepository;
    private final ContactRepository contactRepository;

    public MyLetterListResponse execute(User user) {
        MyLetterListResponse myLetterListResponse = new MyLetterListResponse();
        processLetters(user, myLetterListResponse, letterRepository.findAllByTo(user), false);
        processLetters(user, myLetterListResponse, letterRepository.findAllByFrom(user), true);

        return myLetterListResponse;
    }

    private void processLetters(User user, MyLetterListResponse myLetterListResponse, List<Letter> letters, boolean isSendingLetter) {
        letters.forEach(letter -> {
            String phoneNumber = isSendingLetter ? letter.getTo().getPhoneNumber() : letter.getFrom().getPhoneNumber();
            boolean isContact = contactRepository.existsByUserAndPhoneNumber(user, phoneNumber);
            MyLetterType myLetterType = MyLetterType.determineLetterType(isContact, letter.isArrived(), isSendingLetter);

            String previewText = null;
            if(myLetterType.equals(MyLetterType.WAITING)) {
                previewText = getPreviewText(letter.getText());
            }
            Contact contact = contactRepository.findByUserAndPhoneNumber(user, phoneNumber);
            myLetterListResponse.addMyLetter(new MyLetterDto(myLetterType.getName(), letter.getMediumType(), letter.getCreatedAt(), letter.getArriveAt(), contact, previewText));
        });
    }

    private String getPreviewText(String text) {
        return text.length() > MAX_PREVIEW_TEXT_LENGTH ? text.substring(0, MAX_PREVIEW_TEXT_LENGTH) : text;
    }
}
