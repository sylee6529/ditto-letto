package center.unit.letter.domain.gwiyeoni;

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
@Table(name = "tbl_gwiyeoni")
@Entity
public class Gwiyeoni {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, length = 5)
    private String text;

    @Column(nullable = false, length = 10)
    private String gwiyeoniText;

    public Gwiyeoni(String text, String gwiyeoniText) {
        this.text = text;
        this.gwiyeoniText = gwiyeoniText;
    }
}
