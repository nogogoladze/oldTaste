package ge.conditery.oldTaste.controller;

import ge.conditery.oldTaste.model.Car;
import ge.conditery.oldTaste.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class CarController {
    private final CarService carService;

    @GetMapping("cars")
    public List<Car> getAllCar() {
        return carService.carList(10);
    }

    @PostMapping("car/save")
    public Car saveCar(@RequestBody Car car) {
        return carService.create(car);
    }

    @DeleteMapping("car/delete/{id}")
    public void deleteCar(@PathVariable Integer id) {
        try {
            carService.delete(id);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
