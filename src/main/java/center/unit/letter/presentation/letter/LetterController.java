package center.unit.letter.presentation.letter;

import center.unit.letter.application.letter.QueryLetterByPhoneNumberService;
import center.unit.letter.application.letter.QueryLetterCountService;
import center.unit.letter.application.letter.QueryLetterService;
import center.unit.letter.application.letter.SendLetterService;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.letter.dto.request.SendLetterRequest;
import center.unit.letter.presentation.letter.dto.response.LetterCountResponse;
import center.unit.letter.presentation.letter.dto.response.LetterResponse;
import center.unit.letter.presentation.letter.dto.response.LetterSimpleResponse;
import center.unit.letter.presentation.letter.dto.response.SendLetterResponse;
import center.unit.letter.shared.auth.AuthenticationPrincipal;
import center.unit.letter.shared.response.CommonResponse;
import center.unit.letter.shared.response.ListCommonResponse;
import center.unit.letter.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/letter")
@RestController
public class LetterController {

    private final SendLetterService sendLetterService;
    private final QueryLetterService queryLetterService;
    private final QueryLetterByPhoneNumberService queryLetterByPhoneNumberService;
    private final QueryLetterCountService queryLetterCountService;

    @PostMapping
    public SingleCommonResponse<SendLetterResponse> sendLetter(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid SendLetterRequest request
    ) {
        return CommonResponse.ok(
                sendLetterService.execute(user, request)
        );
    }

    @GetMapping("/{letter-id}")
    public SingleCommonResponse<LetterResponse> queryLetter(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "letter-id") Long id
    ) {
        return CommonResponse.ok(
                queryLetterService.execute(user, id)
        );
    }

    @GetMapping("/count")
    public SingleCommonResponse<LetterCountResponse> queryLetterCount(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "phone-number") String phoneNumber
    ) {
        return CommonResponse.ok(
                queryLetterCountService.execute(user, phoneNumber)
        );
    }

    @GetMapping
    public ListCommonResponse<LetterSimpleResponse> queryLetterByPhoneNumber(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "phone-number") String phoneNumber
    ) {
        return CommonResponse.ok(
                queryLetterByPhoneNumberService.execute(user, phoneNumber)
        );
    }
}
