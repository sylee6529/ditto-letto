package center.unit.letter.infrastructure.persistence.letter;

import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LetterRepository extends CrudRepository<Letter, Long> {

    @Query("select count(l) from Letter l where l.from.id = :fromId and l.to.id = :toId")
    int countByFromAndTo(Long fromId, Long toId);

    @Query("select l from Letter  l where l.from.id = :fromId and l.to.id = :toId and l.arriveAt <= CURRENT_TIMESTAMP")
    List<Letter> findByFromAndTo(Long fromId, Long toId);
}
