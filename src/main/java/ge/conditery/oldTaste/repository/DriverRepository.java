package ge.conditery.oldTaste.repository;

import ge.conditery.oldTaste.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Driver getDriverByPersonalNumber(Integer personalNumber);
}
