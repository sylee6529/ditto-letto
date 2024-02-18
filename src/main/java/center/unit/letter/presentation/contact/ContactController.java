package center.unit.letter.presentation.contact;

import center.unit.letter.application.contact.DeleteContactService;
import center.unit.letter.application.contact.QueryContactService;
import center.unit.letter.application.contact.QueryMyContactService;
import center.unit.letter.application.contact.SaveContactService;
import center.unit.letter.application.contact.UpdateContactService;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.contact.dto.request.CreateContactRequest;
import center.unit.letter.presentation.contact.dto.request.UpdateContactRequest;
import center.unit.letter.presentation.contact.dto.response.ContactDetailResponse;
import center.unit.letter.presentation.contact.dto.response.ContactResponse;
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
import org.springframework.web.bind.annotation.PutMapping;
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
    private final QueryContactService queryContactService;
    private final UpdateContactService updateContactService;
    private final DeleteContactService deleteContactService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void saveContact(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid CreateContactRequest request
    ) {
        saveContactService.execute(user, request);
    }

    @GetMapping
    public ListCommonResponse<ContactResponse> queryMyContact(
            @AuthenticationPrincipal User user
    ) {
        return CommonResponse.ok(
                queryMyContactService.execute(user)
        );
    }

    @GetMapping("/{contact-id}")
    public SingleCommonResponse<ContactDetailResponse> queryContactDetail(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "contact-id") Long id
    ) {
        return CommonResponse.ok(
                queryContactService.execute(user, id)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{contact-id}")
    public void updateContact(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "contact-id") Long id,
            @RequestBody @Valid UpdateContactRequest request
    ) {
        updateContactService.execute(user, id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{contact-id}")
    public void deleteContact(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "contact-id") Long id
    ) {
        deleteContactService.execute(user, id);
    }
}
