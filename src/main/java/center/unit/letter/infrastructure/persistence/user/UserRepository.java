package center.unit.letter.infrastructure.persistence.user;

import center.unit.letter.domain.user.OAuthType;
import center.unit.letter.domain.user.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByOauthTypeAndOauthId(OAuthType oAuthType, String oAuthId);
}
