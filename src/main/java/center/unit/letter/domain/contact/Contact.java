package center.unit.letter.domain.contact;

import center.unit.letter.domain.contact.type.Character;
import center.unit.letter.domain.user.User;
import center.unit.letter.shared.error.BaseException;
import center.unit.letter.shared.error.exception.GlobalErrorCode;
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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_contact")
@Entity
public class Contact {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "feel")
    private Character character;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public Contact(String phoneNumber, String name, Character character, User user) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.character = character;
        this.user = user;
    }

    public void update(String name, Character character, String phoneNumber) {
        this.name = name;
        this.character = character;
        this.phoneNumber = phoneNumber;
    }

    public void ownerIs(User user) {
        if (!user.equals(this.user)) {
            throw new BaseException(GlobalErrorCode.FORBIDDEN);
        }
    }
}
