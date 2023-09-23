package center.unit.letter.infrastructure.persistence.letter;

import center.unit.letter.domain.letter.Letter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LetterRepository extends CrudRepository<Letter, Long> {

    @Query("select count(l) from Letter l where l.from.id = :fromId and l.to.id = :toId")
    int countByFromAndTo(Long fromId, Long toId);
}
