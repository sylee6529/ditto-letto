package center.unit.letter.domain.letter.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediumType {
    // Event Type
    RUDOLPH,
    MAGPIE,

    // Normal Type
    WALK,
    RUN,
    BICYCLE,
    HORSE,
    CAR,
    AIRPLANE,
    DINOSAUR,
    TRAIN;
}
