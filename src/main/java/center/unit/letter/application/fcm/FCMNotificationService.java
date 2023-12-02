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

    public String sendNotification(FCMNotificationRequest request) {
        Optional<User> user = Optional
                .ofNullable(userFacade.getUserById(request.getTargetUserId()));

        if(user.isPresent()) {
            if(user.get().getFcmToken() != null) {
                Notification notification = Notification.builder()
                        .setTitle(request.getTitle())
                        .setBody(request.getBody())
                        .build();
                Message message = Message.builder()
                        .setNotification(notification)
                        .setToken(user.get().getFcmToken())
                        .build();
                try {
                    firebaseMessaging.send(message);
                    return "알림을 성공적으로 전송했습니다. targetUserId: " + request.getTargetUserId() + ", title: " + request.getTitle() + ", body: " + request.getBody();
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                    return "알림 전송에 실패했습니다.";
                }
            }
            else {
                return "유저의 fcmToken이 존재하지 않습니다. targetUserId: " + request.getTargetUserId();
            }
        }
        else {
            return "유저가 존재하지 않습니다. targetUserId: " + request.getTargetUserId();
        }
    }
}
