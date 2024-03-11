package center.unit.letter.presentation.gwiyeoni;

import center.unit.letter.domain.gwiyeoni.Gwiyeoni;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GwiyeoniRepository extends CrudRepository<Gwiyeoni, Long> {

    List<Gwiyeoni> findByTextIn(List<String> textList);
}
