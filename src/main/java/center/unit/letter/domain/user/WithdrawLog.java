package center.unit.letter.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_withdraw_log")
@Entity
public class WithdrawLog {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "oauth_type")
    @Enumerated(EnumType.STRING)
    private OAuthType oauthType;

    @Column(nullable = false, name = "oauth_id")
    private String oauthId;

    private String reason;

    public WithdrawLog(User user, String reason) {
        this.name = user.getName();
        this.userId = user.getId();
        this.oauthType = user.getOauthType();
        this.oauthId = user.getOauthId();
        this.reason = reason;
    }
}
