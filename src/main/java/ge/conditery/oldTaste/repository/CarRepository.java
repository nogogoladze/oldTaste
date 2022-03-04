package ge.conditery.oldTaste.repository;

import ge.conditery.oldTaste.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Car getCarByCarNumber(String carNumber);
}
