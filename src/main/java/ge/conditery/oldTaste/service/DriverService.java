package ge.conditery.oldTaste.service;

import ge.conditery.oldTaste.model.Driver;

import java.util.Collection;

public interface DriverService {
    Driver create(Driver driver);

    Collection<Driver> driverList(int limit);

    Driver get(int id);

    Driver update(Driver driver);

    Boolean delete(int id);

    void relateCar(Integer personalNumber, String carNumber);
}
