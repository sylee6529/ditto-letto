package center.unit.letter.application.letter;

import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.service.UserFacade;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.presentation.letter.dto.response.LetterSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryLetterByPhoneNumberService {

    private final UserFacade userFacade;
    private final LetterRepository letterRepository;

    public List<LetterSimpleResponse> execute(User to, String phoneNumber) {
        User from = userFacade.getUser(phoneNumber);

        return letterRepository.findByFromAndTo(from.getId(), to.getId())
                .stream()
                .map(LetterSimpleResponse::new)
                .toList();
    }
}
