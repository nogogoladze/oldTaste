package ge.conditery.oldTaste.service;

import ge.conditery.oldTaste.model.Car;

import java.util.List;

public interface CarService {
    Car create(Car car);

    List<Car> carList(int limit);

    Car get(int id);

    Car update(Car car);

    Boolean delete(int id);
}
