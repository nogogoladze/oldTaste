package ge.conditery.oldTaste.service.implementation;

import ge.conditery.oldTaste.enums.AppErrorCode;
import ge.conditery.oldTaste.model.Car;
import ge.conditery.oldTaste.repository.CarRepository;
import ge.conditery.oldTaste.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car create(Car car) {
        log.info("Saving new car: " + car.getModel());
        return carRepository.save(car);
    }

    @Override
    public List<Car> carList(int limit) {
        return carRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Car get(int id) {
        log.info("Find car by Id: " + id);
        return carRepository.findById(id).get();
    }

    @Override
    public Car update(Car car) {
        log.info("Update car: " + car.getModel());
        return carRepository.save(car);
    }

    @Override
    public Boolean delete(int id) {
        log.info("Delete car: " + id);

        try {
            carRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Unable to delete " + AppErrorCode.CAR_NOT_EXIST);
        }

        return TRUE;
    }
}
