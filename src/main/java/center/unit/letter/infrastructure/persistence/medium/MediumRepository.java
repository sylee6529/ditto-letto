package center.unit.letter.infrastructure.persistence.medium;

import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.letter.type.MediumType;
import center.unit.letter.domain.medium.Medium;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MediumRepository extends CrudRepository<Medium, Long> {
    @Query("SELECT m FROM Medium m WHERE m.isEvent = false AND m.minDistance <= :distance AND m.maxDistance >= :distance")
    Optional<List<Medium>> findAllByDistance(double distance);

    Optional<List<Medium>> findAllByIsEvent(boolean isEvent);

    Optional<Medium> findByName(MediumType name);
}
