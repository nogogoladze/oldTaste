package ge.conditery.oldTaste.repository;

import ge.conditery.oldTaste.model.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findUserByUserName(String username);
}
