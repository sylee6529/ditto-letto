package center.unit.letter.presentation.contact;

import center.unit.letter.application.contact.DeleteContactService;
import center.unit.letter.application.contact.QueryMyContactService;
import center.unit.letter.application.contact.SaveContactService;
import center.unit.letter.application.contact.UpdateContactService;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.contact.dto.request.ContactRequest;
import center.unit.letter.presentation.contact.dto.response.ContactResponse;
import center.unit.letter.shared.auth.AuthenticationPrincipal;
import center.unit.letter.shared.response.ListCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/contact")
@RestController
public class ContactController {

    private final SaveContactService saveContactService;
    private final QueryMyContactService queryMyContactService;
    private final UpdateContactService updateContactService;
    private final DeleteContactService deleteContactService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void saveContact(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid ContactRequest request
    ) {
        saveContactService.execute(user, request);
    }

    @GetMapping
    public ListCommonResponse<ContactResponse> queryMyContact(
            @AuthenticationPrincipal User user
    ) {
        return queryMyContactService.execute(user);
    }
}
