package ge.conditery.oldTaste.controller;

import ge.conditery.oldTaste.model.Driver;
import ge.conditery.oldTaste.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class DriverController {
    private final DriverService driverService;

    @GetMapping("driver")
    @PreAuthorize("hasRole('USER') or hasRole('ROLE_ADMIN')")
    public Collection<Driver> getAllDriver() {
        return driverService.driverList(10);
    }

    @PostMapping("driver/save")
    @PreAuthorize("hasRole('USER') or hasRole('ROLE_ADMIN')")
    public Driver saveDriver(@RequestBody Driver driver) {
        return driverService.create(driver);
    }


    @PostMapping("driver/car/{personalNumber}/{carNumber}")
    @PreAuthorize("hasRole('USER') or hasRole('ROLE_ADMIN')")
    public void relateCar(@PathVariable Integer personalNumber,
                          @PathVariable String carNumber) {
        driverService.relateCar(personalNumber, carNumber);
    }

    @DeleteMapping("driver/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteDriver(@PathVariable Integer id) {
        try {
            driverService.delete(id);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
