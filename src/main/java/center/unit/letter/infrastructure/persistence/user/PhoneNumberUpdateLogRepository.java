package center.unit.letter.infrastructure.persistence.user;

import center.unit.letter.domain.user.PhoneNumberUpdateLog;
import center.unit.letter.domain.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PhoneNumberUpdateLogRepository extends CrudRepository<PhoneNumberUpdateLog, Long> {
}
