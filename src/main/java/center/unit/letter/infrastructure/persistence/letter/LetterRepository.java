package center.unit.letter.infrastructure.persistence.letter;

import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.user.User;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LetterRepository extends CrudRepository<Letter, Long> {

    @Query("select count(l) from Letter l where l.from.id = :fromId and l.to.id = :toId")
    int countByFromAndTo(Long fromId, Long toId);

    @Query("select l from Letter  l where l.from.id = :fromId and l.to.id = :toId and l.arriveAt <= CURRENT_TIMESTAMP")
    List<Letter> findByFromAndTo(Long fromId, Long toId);

    @Query("SELECT l FROM Letter l WHERE l.arrived = false")
    List<Letter> findAllOfNotArrived();

    List<Letter> findAllByTo(User user);

    List<Letter> findAllByFrom(User user);
}
