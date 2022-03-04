package ge.conditery.oldTaste.service.implementation;

import ge.conditery.oldTaste.enums.AppErrorCode;
import ge.conditery.oldTaste.model.Car;
import ge.conditery.oldTaste.model.Driver;
import ge.conditery.oldTaste.repository.CarRepository;
import ge.conditery.oldTaste.repository.DriverRepository;
import ge.conditery.oldTaste.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;

    @Override
    public Driver create(Driver driver) {
        log.info("Saving new driver: " + driver.getFirstName());
        return driverRepository.save(driver);
    }

    @Override
    public Collection<Driver> driverList(int limit) {
        log.info("Find all driver");
        return driverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Driver get(int id) {
        log.info("Find driver by Id: " + id);
        return driverRepository.findById(id).get();
    }

    @Override
    public Driver update(Driver driver) {
        log.info("Update driver: " + driver.getFirstName());
        return driverRepository.save(driver);
    }

    @Override
    public Boolean delete(int id) {
        log.info("Delete driver: " + id);
        try {
            driverRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Unable to delete " + AppErrorCode.DRIVER_NOT_EXIST);
        }

        return TRUE;
    }

    @Override
    public void relateCar(Integer personalNumber, String carNumber) {
        try {
            Driver getDriver = driverRepository.getDriverByPersonalNumber(personalNumber);
            Car getCar = carRepository.getCarByCarNumber(carNumber);

            getDriver.setCar(getCar);

            driverRepository.save(getDriver);
        } catch (Exception e) {
            log.info("Unable to relate car ");
        }
    }
}
