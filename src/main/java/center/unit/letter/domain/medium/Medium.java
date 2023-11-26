package center.unit.letter.domain.medium;

import center.unit.letter.domain.letter.type.MediumType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.MonthDay;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_medium")
@Entity
public class Medium {

    final static public double DEFAULT_MAX_DISTANCE = 21600;  // 가장 빠른 수단이 24시간 동안 갈 수 있는 거리

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MediumType name;

    @Column(nullable = false)
    private double kmPerHour;

    @Column(nullable = false)
    private double minDistance;

    private Double maxDistance;

    @Column(nullable = false)
    private boolean isEvent;

    @Column(nullable = false)
    private String imgURL;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private LocalDateTime arrivedAt;

    @PostLoad
    private void adjustEventYear() {
        if(this.isEvent) {
            LocalDateTime now = LocalDateTime.now();
            MonthDay nowMonthDay = MonthDay.from(now);
            MonthDay startMonthDay = MonthDay.from(this.startAt);
            MonthDay endMonthDay = MonthDay.from(this.endAt);

            this.startAt = this.startAt.withYear(now.getYear());
            this.endAt = this.endAt.withYear(now.getYear());

            // 다음 해로 넘어가는 경우 처리
            if (startMonthDay.isBefore(nowMonthDay)) {
                this.startAt = startAt.plusYears(1);
            }
            if (endMonthDay.isBefore(nowMonthDay)) {
                this.endAt = endAt.plusYears(1);
            }

        }
    }

    public int calculateTravelTime(double distance) {
        final int MAX_TIME = 86400; // 24시간
        if (this.kmPerHour == 0) {
            throw new IllegalArgumentException("거리는 0이 될 수 없습니다.");
        }

        double timeInHours = distance / this.kmPerHour;

        // 초단위로 변환
        double timeInSeconds = timeInHours * 3600;

        // 1초 미만은 1초로 변경
        if (timeInSeconds < 1) {
            return 1;
        }
        if (timeInSeconds > MAX_TIME) {
            return MAX_TIME;
        }

        return (int) timeInSeconds;
    }
}
