package center.unit.letter.application.medium;

import center.unit.letter.domain.letter.type.MediumType;
import center.unit.letter.domain.medium.Medium;
import center.unit.letter.domain.medium.exception.MediumErrorCode;
import center.unit.letter.domain.medium.exception.MediumException;
import center.unit.letter.infrastructure.persistence.medium.MediumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class QueryMediumService {

        private final MediumRepository mediumRepository;

        public Medium execute(MediumType name) {
                return mediumRepository.findByName(name)
                        .orElseThrow(() -> new MediumException(MediumErrorCode.INVALID_MEDIUM_NAME));
        }

        @Transactional
        public Medium getMediumByDistance(double distance) {
                // 거리가 최대 거리 이상이면, 최대 거리로 설정
                distance = Math.min(distance, Medium.DEFAULT_MAX_DISTANCE);


                // 이벤트 타입까지 포함하여 타입을 결정
                List<Medium> mediumList = mediumRepository.findAllByDistance(distance)
                        .orElseThrow(() -> new MediumException(MediumErrorCode.MEDIUM_NOT_FOUND));
                mediumList.addAll(mediumRepository.findAllByIsEvent(true)
                        .orElseThrow(() -> new MediumException(MediumErrorCode.EVENT_MEDIUM_NOT_FOUND)));

                return mediumList.get(new Random().nextInt(mediumList.size()));
        }
}
