package center.unit.letter.domain.user.service;

import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.exception.UserNotFoundException;
import center.unit.letter.infrastructure.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getUser(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
