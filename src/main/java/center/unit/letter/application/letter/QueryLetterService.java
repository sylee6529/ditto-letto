package center.unit.letter.application.letter;

import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.presentation.letter.dto.response.LetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryLetterService {

    private final LetterRepository letterRepository;

    public LetterResponse execute(User user, Long id) {
        Letter letter = getLetter(id);
        letter.isTo(user);

        return new LetterResponse(
                letter,
                letterRepository.countByFromAndTo(user.getId(), letter.getFrom().getId()),
                letterRepository.countByFromAndTo(letter.getFrom().getId(), user.getId())
        );
    }

    private Letter getLetter(Long id) {
        return letterRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
