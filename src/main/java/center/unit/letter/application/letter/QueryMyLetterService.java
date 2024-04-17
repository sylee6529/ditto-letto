package center.unit.letter.application.letter;

import center.unit.letter.application.contact.QueryMyContactService;
import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.letter.type.MyLetterType;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.persistence.contact.ContactRepository;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.infrastructure.persistence.user.UserRepository;
import center.unit.letter.presentation.letter.dto.MyLetterVO;
import center.unit.letter.presentation.letter.dto.response.MyLetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryMyLetterService {

    private final LetterRepository letterRepository;
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public List<MyLetterResponse> execute(User user) {
        List<MyLetterVO> myLetters = new ArrayList<>();
        processLetters(user, myLetters, letterRepository.findAllByTo(user), false);
        processLetters(user, myLetters, letterRepository.findAllByFrom(user), true);

        return myLetters.stream()
                .map(MyLetterResponse::new)
                .toList();
    }

    private void processLetters(User user, List<MyLetterVO> myLetters, List<Letter> letters, boolean isSendingLetter) {
        letters.forEach(letter -> {
            User otherUser = isSendingLetter? letter.getTo() : letter.getFrom();

            if(!userRepository.existsById(otherUser.getId())) {
                MyLetterType myLetterType = MyLetterType.determineLetterType(false, letter.isArrived(), isSendingLetter);
                String previewText = myLetterType == MyLetterType.WAITING? letter.getPreviewText() : null;

                myLetters.add(
                        new MyLetterVO(
                                letter.getId(),
                                myLetterType.getName(),
                                letter.getMediumType(),
                                letter.getCreatedAt(),
                                letter.getArriveAt(),
                                previewText
                        )
                );
            } else {
                String phoneNumber = otherUser.getPhoneNumber();
                boolean isContact = contactRepository.existsByUserAndPhoneNumber(user, phoneNumber);
                MyLetterType myLetterType = MyLetterType.determineLetterType(isContact, letter.isArrived(), isSendingLetter);
                Contact contact = contactRepository.findByUserAndPhoneNumber(user, phoneNumber);
                String previewText = myLetterType.equals(MyLetterType.WAITING)? letter.getPreviewText() : null;

                myLetters.add(
                        new MyLetterVO(
                                letter.getId(),
                                myLetterType.getName(),
                                letter.getMediumType(),
                                letter.getCreatedAt(),
                                letter.getArriveAt(),
                                contact,
                                previewText,
                                phoneNumber
                        )
                );
            }
        });
    }


}
