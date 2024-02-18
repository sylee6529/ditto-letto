package center.unit.letter.shared.util.vo;

import center.unit.letter.domain.letter.exception.LetterErrorCode;
import center.unit.letter.domain.letter.exception.LetterException;
import lombok.Getter;

@Getter
public class Location {
    private Double latitude;
    private Double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = formatDouble(latitude);
        this.longitude = formatDouble(longitude);
    }

    public double getDistanceFrom(Location location) {
        double startLat = this.latitude;
        double startLon = this.longitude;
        double endLat = location.getLatitude();
        double endLon = location.getLongitude();

        if (startLat > 90 || startLat < -90 || endLat > 180 || endLat < -180) {
            throw new LetterException(LetterErrorCode.INVALID_LOCATION_VALUE);
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

    public double formatDouble(double value) {
        String format = String.format("%.6f", value);
        return Double.parseDouble(format);
    }
}

