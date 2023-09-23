package center.unit.letter.domain.letter.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediumType {
    WALK(4),
    RUN(3),
    BICYCLE(2),
    PLAIN(1);

    private final int speed;
}
