package center.unit.letter.domain.letter.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediumType {
    WALK(60),
    RUN(50),
    BICYCLE(40),
    HORSE(30),
    CAR(20),
    PLAIN(10);

    private final int speed;
}
