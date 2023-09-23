package center.unit.letter.infrastructure.persistence.user;

import center.unit.letter.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);
}
