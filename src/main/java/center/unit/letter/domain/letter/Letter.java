package center.unit.letter.domain.letter;

import center.unit.letter.domain.letter.type.LetterType;
import center.unit.letter.domain.letter.type.MediumType;
import center.unit.letter.domain.user.User;
import center.unit.letter.shared.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_letter")
@Entity
public class Letter extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, length = 144)
    private String text;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MediumType mediumType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LetterType type;

    @Column(nullable = false)
    private LocalDateTime arriveAt;

    @Column(nullable = false)
    private boolean arrived;

    @JoinColumn(name = "to_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User to;

    @JoinColumn(name = "from_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User from;

    public Letter(String text, MediumType mediumType, LetterType type, User to, User from, LocalDateTime arriveAt) {
        this.text = text;
        this.mediumType = mediumType;
        this.type = type;
        this.arriveAt = arriveAt;
        this.to = to;
        this.from = from;
        this.arrived = false;
    }

    public void isTo(User user) {
        if (!user.getId().equals(to.getId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }

    public void arrive() {
        this.arrived = true;
    }

    public int getNow() {
        LocalDateTime currentTime = LocalDateTime.now();
        if (arrived) {
            return 7;
        }

        Duration duration = Duration.between(getCreatedAt(), arriveAt);
        long slotDuration = duration.toMillis() / 7;
        Duration elapsedTime = Duration.between(getCreatedAt(), currentTime);

        return (int) (elapsedTime.toMillis() / slotDuration) + 1;
    }
}
