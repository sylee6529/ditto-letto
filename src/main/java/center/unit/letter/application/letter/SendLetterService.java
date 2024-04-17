package center.unit.letter.application.letter;

import center.unit.letter.application.medium.QueryMediumService;
import center.unit.letter.application.user.UpdateUserService;
import center.unit.letter.domain.gwiyeoni.service.GwiyeoniService;
import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.letter.exception.FromLocationNotFoundException;
import center.unit.letter.domain.letter.exception.ToLocationNotFoundException;
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

import center.unit.letter.presentation.user.dto.request.UpdateUserLocationRequest;
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
    private final UpdateUserService updateUserService;

    @Transactional
    public SendLetterResponse execute(
            User fromUser,
            SendLetterRequest request
    ) {
        User toUser = userFacade.getUser(request.getToPhoneNumber());

        validate(request, toUser, fromUser);
        updateLocation(fromUser, request);

        Location fromLocation = fromUser.getLocation();
        Location toLocation = toUser.getLocation();

        // 위치를 계산하여 탈 것을 랜덤으로 배정
        double distance = fromLocation.getDistanceFrom(toLocation);

        Medium medium = queryMediumService.getMediumByDistance(distance);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime arrivedAt = medium.isEvent() ? medium.getArrivedAt() : LocalDateTime.now().plusSeconds(medium.calculateTravelTime(distance));

        Letter letter = letterRepository.save(
                new Letter(
                        convertText(request.getText(), request.getType()),
                        medium.getName(),
                        request.getType(),
                        toUser,
                        fromUser,
                        arrivedAt
                )
        );

        return new SendLetterResponse(
                letter.getArriveAt().format(dateTimeFormatter),
                letter.getMediumType(),
                letter.getCreatedAt().format(dateTimeFormatter),
                medium.getImgURL()
        );
    }

    private String convertText(String text, LetterType type) {
        return type.equals(LetterType.CODE) ?
                gwiyeoniService.convertToGwiyeoniText(text) :
                text;
    }

    private void validate(
            SendLetterRequest request,
            User toUser,
            User fromUser
    ) {
        // request에 위치 정보가 있으면, toUser의 위치 정보가 없을 때만 에러
        if(request.getLatitude() != null && request.getLongitude() != null) {
            Optional.ofNullable(toUser.getLocation())
                    .orElseThrow(ToLocationNotFoundException::new);
        }

        // request에 위치 정보가 없고 fromUser와 toUser의 위치 정보가 없으면, 에러
        else {
            Optional.ofNullable(fromUser.getLocation())
                    .orElseThrow(FromLocationNotFoundException::new);
            Optional.ofNullable(toUser.getLocation())
                    .orElseThrow(ToLocationNotFoundException::new);
        }
    }

    public void updateLocation(User fromUser, SendLetterRequest request) {
        if(request.getLatitude() != null && request.getLongitude() != null) {
            updateUserService.updateLocation(fromUser, new UpdateUserLocationRequest(request.getLongitude(), request.getLatitude()));
        }
    }
}
