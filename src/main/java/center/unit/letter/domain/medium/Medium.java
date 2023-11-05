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

    private LocalDateTime arriveAt;

    @PrePersist
    public void setDefaultMaxDistance() {
        if (this.maxDistance == null) {
            this.maxDistance = DEFAULT_MAX_DISTANCE;
        }
    }

    public void adjustEventYear() {
        if (isEvent) {
            this.arriveAt = adjustEventYear(this.arriveAt);
        }
    }

    private LocalDateTime adjustEventYear(LocalDateTime arriveAt) {
        LocalDateTime now = LocalDateTime.now();
        MonthDay nowMonthDay = MonthDay.from(now);
        MonthDay arriveMonthDay = MonthDay.from(arriveAt);

        if (arriveMonthDay.equals(nowMonthDay)) {   // 이벤트 날짜가 오늘인 경우, now 시간으로 설정
            return now;
        } else if (arriveMonthDay.isBefore(nowMonthDay)) {  // 이벤트 날짜가 지난 날짜인 경우, 내년으로 설정

            return LocalDateTime.of(now.getYear() + 1, arriveAt.getMonth(), arriveAt.getDayOfMonth(), arriveAt.getHour(), arriveAt.getMinute());
        } else {    // 이벤트 날짜가 미래의 날짜인 경우, 이번 년도로 설정
            return LocalDateTime.of(now.getYear(), arriveAt.getMonth(), arriveAt.getDayOfMonth(), arriveAt.getHour(), arriveAt.getMinute());
        }
    }
}
