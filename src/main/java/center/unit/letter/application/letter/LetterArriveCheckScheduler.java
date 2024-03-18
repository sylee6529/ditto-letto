package center.unit.letter.application.letter;

import center.unit.letter.application.fcm.FCMNotificationService;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import java.time.LocalDateTime;

import center.unit.letter.presentation.fcm.dto.request.FCMNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LetterArriveCheckScheduler {

    private final LetterRepository letterRepository;
    private final FCMNotificationService fcmNotificationService;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void checkArriveAndPushNotification() {
        letterRepository.findAllOfNotArrived()
            .forEach(letter -> {
                if (LocalDateTime.now().isAfter(letter.getArriveAt())) {
                    letter.arrive();
                    log.info(
                        "letter arrived from {} to {}",
                        letter.getFrom().getPhoneNumber(),
                        letter.getTo().getPhoneNumber()
                    );

                    fcmNotificationService.sendNotification(new FCMNotificationRequest(
                        letter.getTo().getId(),
                        "\uD83D\uDC8C 새로운 편지가 도착했어요!",
                            letter.getPreviewText()
                    ));
                }
            });
    }
}
