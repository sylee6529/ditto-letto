package center.unit.letter.domain.user;

import center.unit.letter.shared.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_phone_number_log")
@Entity
public class PhoneNumberUpdateLog extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String beforePhoneNumber;

    @Column(nullable = false)
    private String afterPhoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public PhoneNumberUpdateLog(String beforePhoneNumber, String afterPhoneNumber, User user) {
        this.beforePhoneNumber = beforePhoneNumber;
        this.afterPhoneNumber = afterPhoneNumber;
        this.user = user;
    }
}
