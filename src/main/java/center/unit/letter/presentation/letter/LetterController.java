package center.unit.letter.presentation.letter;

import center.unit.letter.application.letter.*;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.letter.dto.request.SendLetterRequest;
import center.unit.letter.presentation.letter.dto.response.*;
import center.unit.letter.shared.auth.AuthenticationPrincipal;
import center.unit.letter.shared.response.CommonResponse;
import center.unit.letter.shared.response.ListCommonResponse;
import center.unit.letter.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/letter")
@RestController
public class LetterController {

    private final SendLetterService sendLetterService;
    private final QueryLetterService queryLetterService;
    private final QueryLetterByPhoneNumberService queryLetterByPhoneNumberService;
    private final QueryLetterCountService queryLetterCountService;
    private final DeleteLetterService deleteLetterService;
    private final QueryMyLetterService queryMyLetterService;

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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{letter-id}")
    public void deleteLetter(
        @AuthenticationPrincipal User user,
        @PathVariable(name = "letter-id") Long letterId) {
        deleteLetterService.deleteLetter(user, letterId);
    }

    @GetMapping("/my")
    public SingleCommonResponse<MyLetterListResponse> queryLetterCount(
            @AuthenticationPrincipal User user
    ) {
        return CommonResponse.ok(
                queryMyLetterService.execute(user)
        );
    }
}
