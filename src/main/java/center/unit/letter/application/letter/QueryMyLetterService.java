package center.unit.letter.application.letter;

import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.service.UserFacade;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.presentation.letter.dto.response.LetterCountResponse;
import center.unit.letter.presentation.letter.dto.response.LetterSimpleResponse;
import center.unit.letter.presentation.letter.dto.response.MyLetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryMyLetterService {
    private final UserFacade userFacade;
    private final LetterRepository letterRepository;


    public List<MyLetterResponse> execute(User user) {
        return letterRepository.findAllByUserId(user.getId());
    }
}
