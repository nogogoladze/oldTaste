package ge.conditery.oldTaste.service.implementation;

import ge.conditery.oldTaste.model.system.User;
import ge.conditery.oldTaste.repository.UserRepository;
import ge.conditery.oldTaste.service.UserService;
import ge.conditery.oldTaste.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserDetailsServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);
        return UserDetailsImpl.build(user);
    }

    @Override
    public User saveSystemUser(User systemUser) {
        log.info("Saving new user: " + systemUser.getUserName());
        User userExist = userRepository.findByEmail(systemUser.getEmail());
        if (userExist != null && (userExist.getEmail().equals(systemUser.getEmail()) || userExist.getUserName().equals(systemUser.getUserName()))) {
            log.error("email exist");
            return null;
        } else {
            systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
            systemUser.setActive(true);
            systemUser.setRandomNumber(Utils.getRandomNumber());
            systemUser.setRole(systemUser.getRole());
            return userRepository.save(systemUser);
        }
    }
}
