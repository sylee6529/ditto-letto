package center.unit.letter.application.fcm;

import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.service.UserFacade;
import center.unit.letter.presentation.fcm.dto.request.FCMNotificationRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.JavaServiceLoadable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {

    private final FirebaseMessaging firebaseMessaging;
    private final UserFacade userFacade;

    public void sendNotification(FCMNotificationRequest request) {
        User user = userFacade.getUserById(request.getTargetUserId());

        try {
            if(user != null && user.getFcmToken() != null) {
                Notification notification = Notification.builder()
                        .setTitle(request.getTitle())
                        .setBody(request.getBody())
                        .build();
                Message message = Message.builder()
                        .setNotification(notification)
                        .setToken(user.getFcmToken())
                        .build();

                firebaseMessaging.send(message);
            }
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException("알림 전송에 실패했습니다.");
        }
    }
}
