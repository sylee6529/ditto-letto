package center.unit.letter.presentation.user;

import center.unit.letter.application.user.PhoneNumberDuplicateCheckService;
import center.unit.letter.application.user.QueryUserService;
import center.unit.letter.application.user.UpdateUserPhoneNumberService;
import center.unit.letter.application.user.UpdateUserService;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.user.dto.request.UpdateUserPhoneNumberRequest;
import center.unit.letter.presentation.user.dto.request.UpdateUserRequest;
import center.unit.letter.presentation.user.dto.response.PhoneNumberDuplicateCheckResponse;
import center.unit.letter.presentation.user.dto.response.UserResponse;
import center.unit.letter.shared.auth.AuthenticationPrincipal;
import center.unit.letter.shared.response.CommonResponse;
import center.unit.letter.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final QueryUserService queryUserService;
    private final UpdateUserService updateUserService;
    private final UpdateUserPhoneNumberService updateUserPhoneNumberService;
    private final PhoneNumberDuplicateCheckService phoneNumberDuplicateCheckService;

    @GetMapping
    public SingleCommonResponse<UserResponse> getUser(
            @AuthenticationPrincipal User user
    ) {
        return CommonResponse.ok(
                queryUserService.execute(user)
        );
    }

    // TODO: 12/3/23 UserPhoneNumber 변경을 포함하고 있어서 수 정이 필요할 것 같습니다.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updateUser(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UpdateUserRequest request
    ) {
        updateUserService.execute(user, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/phone-number")
    public void updatePhoneNumber(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UpdateUserPhoneNumberRequest request
            ) {
        updateUserPhoneNumberService.execute(user, request);
    }

    @GetMapping("/phone-number/duplicate-check")
    public SingleCommonResponse<PhoneNumberDuplicateCheckResponse> phoneNumberDuplicateCheck(@RequestParam(value = "phone-number") String phoneNumber) {
        return CommonResponse.ok(phoneNumberDuplicateCheckService.execute(phoneNumber));
    }
}
