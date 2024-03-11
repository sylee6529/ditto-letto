package center.unit.letter.presentation.fcm;

import center.unit.letter.application.fcm.FCMNotificationService;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.fcm.dto.request.FCMNotificationRequest;
import center.unit.letter.shared.auth.AuthenticationPrincipal;
import center.unit.letter.shared.response.CommonResponse;
import center.unit.letter.shared.response.SingleCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notification")
public class FCMNotificationController {
    private final FCMNotificationService fcmNotificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendNotification(@RequestBody FCMNotificationRequest request) {
        fcmNotificationService.sendNotification(request);
    }

    @PutMapping("/token")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setToken(@AuthenticationPrincipal User user, @RequestParam String token) {
        user.updateFcmToken(token);
    }
}
