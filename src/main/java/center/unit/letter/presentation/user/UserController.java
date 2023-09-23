package center.unit.letter.presentation.user;

import center.unit.letter.application.user.QueryUserService;
import center.unit.letter.application.user.UpdateUserService;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.user.dto.request.UpdateUserRequest;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final QueryUserService queryUserService;
    private final UpdateUserService updateUserService;

    @GetMapping
    public SingleCommonResponse<UserResponse> getUser(
            @AuthenticationPrincipal User user
    ) {
        return CommonResponse.ok(
                queryUserService.execute(user)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updateUser(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UpdateUserRequest request
    ) {
        updateUserService.execute(user, request);
    }
}
