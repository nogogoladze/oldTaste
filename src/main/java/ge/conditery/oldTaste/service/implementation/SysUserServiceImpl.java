package ge.conditery.oldTaste.service.implementation;

import ge.conditery.oldTaste.model.system.SystemUser;
import ge.conditery.oldTaste.repository.SystemUserRepository;
import ge.conditery.oldTaste.service.SystemUserService;
import ge.conditery.oldTaste.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SysUserServiceImpl implements SystemUserService, UserDetailsService {
    private final SystemUserRepository systemUserRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserRepository.findSystemUserByUserName(username);
        if (systemUser == null) {
            log.info("User not found: " + username);
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found in the database: {}", username);

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            systemUser.getRole().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });

            return new org.springframework.security.core.userdetails.User(systemUser.getUserName(), systemUser.getPassword(), authorities);

        }
    }

    @Override
    public SystemUser saveSystemUser(SystemUser systemUser) {
        log.info("Saving new user: " + systemUser.getUserName());
        SystemUser userExist = systemUserRepository.findByEmail(systemUser.getEmail());
        if (userExist != null && (userExist.getEmail().equals(systemUser.getEmail()) || userExist.getUserName().equals(systemUser.getUserName()))) {
            log.error("email exist");
            return null;
        } else {
            systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
            systemUser.setActive(true);
            systemUser.setRandomNumber(Utils.getRandomNumber());
            systemUser.setRole(systemUser.getRole());
            return systemUserRepository.save(systemUser);
        }
    }
}
