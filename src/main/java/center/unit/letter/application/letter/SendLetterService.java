package center.unit.letter.application.letter;

import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.letter.type.LetterType;
import center.unit.letter.domain.letter.type.MediumType;
import center.unit.letter.domain.medium.Medium;
import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.service.UserFacade;
import center.unit.letter.infrastructure.cyworld.CyworldService;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.infrastructure.persistence.medium.MediumRepository;
import center.unit.letter.presentation.letter.dto.request.SendLetterRequest;
import center.unit.letter.presentation.letter.dto.response.SendLetterResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SendLetterService {

    private final CyworldService cyworldService;
    private final UserFacade userFacade;
    private final LetterRepository letterRepository;
    private final MediumRepository mediumRepository;

    public SendLetterResponse execute(
        User from,
        SendLetterRequest request
    ) {
        MediumType mediumType;
        LocalDateTime arriveAt;
        String imgUrl;

        // request에 있는 경도, 위도를 소수점 6자리로 포맷팅한다
        double startLatitude = formatDouble(request.getStartLatitude());
        double startLongitude = formatDouble(request.getStartLongitude());
        double endLatitude = formatDouble(request.getEndLatitude());
        double endLongitude = formatDouble(request.getEndLongitude());

        // 일반 타입이면, 거리로 시간을 계산하여 도착시간을 정한다
        if (request.getEventType() == null) {
            double distance = calculateDistanceInKilometer(startLatitude, startLongitude, endLatitude, endLongitude);

            // 거리로 타입을 랜덤으로 설정
            Medium medium = getMediumTypeByDistance(distance);
            mediumType = medium.getName();
            imgUrl = medium.getImgURL();

            // 선택된 탈 것을 타고 가는데 걸리는 시간을 계산(초)
            int travelTime = calculateTravelTime(
                    distance,
                    medium.getKmPerHour()
            );
            arriveAt = LocalDateTime.now().plusSeconds(travelTime);
        }

        // 이벤트 타입인 경우, 이벤트 타입을 검색하여 도착시간과 imgURL을 가져온다
        else {
            Medium eventMedium = mediumRepository.findByName(request.getEventType());
            eventMedium.adjustEventYear();  // 현재 날짜에 따라 이벤트 연도(올해, 내년)를 조정

            mediumType = eventMedium.getName();
            arriveAt = eventMedium.getArriveAt();
            imgUrl = eventMedium.getImgURL();
        }

        Letter letter = letterRepository.save(
            new Letter(
                convertText(request.getText(), request.getType()),
                mediumType,
                request.getType(),
                userFacade.getUser(request.getTargetPhoneNumber()),
                from,
                    arriveAt
            )
        );

        return new SendLetterResponse(letter, imgUrl);
    }

    private String convertText(String text, LetterType type) {
        return type.equals(LetterType.CODE) ?
            cyworldService.execute(text) :
            text;
    }

    private Medium getMediumTypeByDistance(double distance) {
        // 거리가 최대 거리 이상이면, 최대 거리로 설정
        if(distance > Medium.DEFAULT_MAX_DISTANCE) {
            distance = Medium.DEFAULT_MAX_DISTANCE;
        }
        List<Medium> mediumList = mediumRepository.findAllByDistance(distance);

        return mediumList.get(new Random().nextInt(mediumList.size()));
    }

    private static double calculateDistanceInKilometer(double startLat, double startLon, double endLat, double endLon) {

        if (startLat > 90 || startLat < -90 || endLat > 180 || endLat < -180) {
            throw new IllegalArgumentException("위도는 -90 ~ 90 사이, 경도는 -180 ~ 180 사이의 값을 가져야 합니다.");
        }

        if (startLon == endLon && startLat == endLat) {
            return 0;
        }

        final int EARTH_RADIUS = 6371; // 지구의 둘레를 360도로 나눈 값

        // 각도를 라디안으로 변환
        double dLat = Math.toRadians(endLat - startLat);
        double dLong = Math.toRadians(endLon - startLon);

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        // haversine 공식 적용
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(startLat) * Math.cos(endLat) * Math.pow(Math.sin(dLong / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public int calculateTravelTime(double distance, double speed) {
        final int MAX_TIME = 86400; // 24시간
        if (speed == 0) {
            throw new IllegalArgumentException("거리는 0이 될 수 없습니다.");
        }

        double timeInHours = distance / speed;

        // 초단위로 변환
        double timeInSeconds = timeInHours * 3600;

        // 1초 미만은 1초로 변경
        if (timeInSeconds < 1) {
            return 1;
        }
        if (timeInSeconds > MAX_TIME) {
            return MAX_TIME;
        }

        return (int) timeInSeconds;
    }

    public static double formatDouble(double value) {
        String format = String.format("%.6f", value);
        return Double.parseDouble(format);
    }
}
