package center.unit.letter.presentation.fcm.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequest {
    private Long targetUserId;
    private String title;
    private String body;

    @Builder
    public FCMNotificationRequest(Long targetUserId, String title, String body) {
        this.targetUserId = targetUserId;
        this.title = title;
        this.body = body;
    }
}
