package center.unit.letter.infrastructure.persistence.user;

import center.unit.letter.domain.user.WithdrawLog;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawLogRepository extends CrudRepository<WithdrawLog, Long> {

}
