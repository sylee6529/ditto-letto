package center.unit.letter.presentation.letter;

import center.unit.letter.application.letter.SendLetterService;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.letter.dto.request.SendLetterRequest;
import center.unit.letter.presentation.letter.dto.response.SendLetterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/letter")
@RestController
public class LetterController {

    private final SendLetterService sendLetterService;

    @PostMapping
    public SendLetterResponse sendLetter(
            User user,
            @RequestBody @Valid SendLetterRequest request
    ) {
        return sendLetterService.execute(user, request);
    }
}
