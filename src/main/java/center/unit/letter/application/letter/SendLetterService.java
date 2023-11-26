package center.unit.letter.application.letter;

import center.unit.letter.application.medium.QueryMediumService;
import center.unit.letter.domain.gwiyeoni.service.GwiyeoniService;
import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.letter.exception.LetterErrorCode;
import center.unit.letter.domain.letter.exception.LetterException;
import center.unit.letter.domain.letter.type.LetterType;
import center.unit.letter.domain.medium.Medium;
import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.service.UserFacade;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.presentation.letter.dto.request.SendLetterRequest;
import center.unit.letter.presentation.letter.dto.response.SendLetterResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import center.unit.letter.shared.util.vo.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SendLetterService {

    private final UserFacade userFacade;
    private final LetterRepository letterRepository;
    private final QueryMediumService queryMediumService;
    private final GwiyeoniService gwiyeoniService;

    @Transactional
    public SendLetterResponse execute(
            User from,
            SendLetterRequest request
    ) {
        Optional<Location> fromLocation = Optional.ofNullable(from.getLocation());
        User toUser = userFacade.getUserById(request.getToUserId());
        Optional<Location> toLocation = Optional.ofNullable(toUser.getLocation());

        fromLocation.orElseThrow(() -> new LetterException(LetterErrorCode.FROM_LOCATION_NOT_FOUND));
        toLocation.orElseThrow(() -> new LetterException(LetterErrorCode.TO_LOCATION_NOT_FOUND));

        double distance = fromLocation.get().getDistanceFrom(toLocation.get());
        Medium medium = queryMediumService.getMediumByDistance(distance);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 이벤트 타입인 경우, 이벤트 날짜를 적용하여 저장
        if (medium.isEvent()) {
            Letter letter = letterRepository.save(
                    new Letter(
                            convertText(request.getText(), request.getType()),
                            medium.getName(),
                            request.getType(),
                            userFacade.getUserById(request.getToUserId()),
                            from,
                            medium.getArrivedAt()
                    )
            );

            return new SendLetterResponse(
                    letter.getArriveAt().format(dateTimeFormatter),
                    letter.getMediumType(),
                    letter.getCreatedAt().format(dateTimeFormatter),
                    medium.getImgURL()
            );
        }

        // 일반 타입이면, 거리와 정해진 탈 것의 시속으로 도착 날짜를 적용하여 저장
        else {
            Letter letter = letterRepository.save(
                    new Letter(
                            convertText(request.getText(), request.getType()),
                            medium.getName(),
                            request.getType(),
                            userFacade.getUserById(request.getToUserId()),
                            from,
                            LocalDateTime.now().plusSeconds(
                                    medium.calculateTravelTime(distance)
                            )
                    )
            );

            return new SendLetterResponse(
                    letter.getArriveAt().format(dateTimeFormatter),
                    letter.getMediumType(),
                    letter.getCreatedAt().format(dateTimeFormatter),
                    medium.getImgURL()
            );

        }
    }

    private String convertText(String text, LetterType type) {
        return type.equals(LetterType.CODE) ?
                gwiyeoniService.convertToGwiyeoniText(text) :
                text;
    }
}
