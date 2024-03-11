package center.unit.letter.application.letter;

import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.shared.error.BaseException;
import center.unit.letter.shared.error.exception.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteLetterService {

    private final LetterRepository letterRepository;

    public void deleteLetter(User author, Long letterId) {
        // TODO: 리소스 없음 에러
        Letter letter = letterRepository.findById(letterId)
            .orElseThrow(() -> new BaseException(GlobalErrorCode.BAD_REQUEST));

        // TODO: 권한 에러
        if (!letter.getTo().equals(author)) {
            throw new BaseException(GlobalErrorCode.BAD_REQUEST);
        }

        letterRepository.delete(letter);
    }
}
