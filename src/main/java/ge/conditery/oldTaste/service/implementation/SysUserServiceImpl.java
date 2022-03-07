package ge.conditery.oldTaste.service.implementation;

import ge.conditery.oldTaste.model.system.SystemUser;
import ge.conditery.oldTaste.repository.SystemUserRepository;
import ge.conditery.oldTaste.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserRepository.findSystemUserByUserName(username);
        if (systemUser == null) {
            log.info("user not found");
        }

        Collection<SimpleGrantedAuthority> autorities = new ArrayList<>();
        assert systemUser != null;
            autorities.add(new SimpleGrantedAuthority("admin"));

        return new User(systemUser.getUserName(), systemUser.getPassword(), systemUser.getActive(), true, true, true, autorities);
    }

    @Override
    public SystemUser saveSystemUser(SystemUser systemUser) {
        log.info("Saving new user");
        return systemUserRepository.save(systemUser);
    }

    @Override
    public boolean findByEmail(String email) {
        return systemUserRepository.findByEmail(email);
    }

}
