package center.unit.letter.infrastructure.persistence.letter;

import center.unit.letter.domain.letter.Letter;
import org.springframework.data.repository.CrudRepository;

public interface LetterRepository extends CrudRepository<Letter, Long> {
}
