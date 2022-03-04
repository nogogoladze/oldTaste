package ge.conditery.oldTaste.repository;

import ge.conditery.oldTaste.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location getLocationByAddress(String address);
}
