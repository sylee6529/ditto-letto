package center.unit.letter.domain.user;

import center.unit.letter.shared.entity.BaseTimeEntity;
import center.unit.letter.shared.util.vo.Location;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
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

    @Column(nullable = false)
    private long kakaoUserId;

    private Double longitude;

    private Double latitude;

    public User(String name, String phoneNumber, long kakaoUserId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.kakaoUserId = kakaoUserId;
        this.longitude = null;
        this.latitude = null;
    }

    public void update(String name, String phoneNumber) {
        this.name = name;
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
