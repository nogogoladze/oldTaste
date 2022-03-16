package ge.conditery.oldTaste.repository;

import ge.conditery.oldTaste.enums.ERole;
import ge.conditery.oldTaste.model.system.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}