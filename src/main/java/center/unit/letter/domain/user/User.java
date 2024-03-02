package center.unit.letter.domain.user;

import center.unit.letter.shared.entity.BaseTimeEntity;
import center.unit.letter.shared.util.vo.Location;
import center.unit.letter.shared.util.vo.Location;
import jakarta.persistence.*;
import lombok.AccessLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
@Entity
public class User extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, name = "oauth_id")
    private String oauthId;

    private String fcmToken;

    @Column(nullable = false, name = "oauth_type")
    @Enumerated(EnumType.STRING)
    private OAuthType oauthType;

    private Double longitude;

    private Double latitude;

    public User(String name, String phoneNumber, OAuthType oauthType, String oauthId, Double longitude, Double latitude) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.oauthType = oauthType;
        this.oauthId = oauthId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void update(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void updateFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void updateLocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location getLocation() {
        if(this.longitude == null || this.latitude == null)
            return null;
        return new Location(this.latitude, this.longitude);
    }
}
