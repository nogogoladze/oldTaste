package ge.conditery.oldTaste.repository;

import ge.conditery.oldTaste.model.system.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {

    SystemUser findByEmail(String email);

    SystemUser findSystemUserByUserName(String username);
}
