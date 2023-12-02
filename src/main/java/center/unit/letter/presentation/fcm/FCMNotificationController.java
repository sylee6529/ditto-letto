package center.unit.letter.presentation.fcm;

import center.unit.letter.application.fcm.FCMNotificationService;
import center.unit.letter.presentation.fcm.dto.request.FCMNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notification")
public class FCMNotificationController {
    private final FCMNotificationService fcmNotificationService;

    public String sendNotification(@RequestBody FCMNotificationRequest request) {
        return fcmNotificationService.sendNotification(request);
    }
}
