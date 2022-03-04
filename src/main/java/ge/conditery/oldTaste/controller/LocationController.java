package ge.conditery.oldTaste.controller;

import ge.conditery.oldTaste.model.Location;
import ge.conditery.oldTaste.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/")
public class LocationController {
    private final LocationService locationService;

    @GetMapping("location")
    public Collection<Location> getAllLocation() {
        return locationService.locationList(10);
    }

    @PostMapping("location/save")
    public Location saveLocation(@RequestBody Location location) {
        return locationService.create(location);
    }

    @DeleteMapping("location/delete/{id}")
    public void deleteDriver(@PathVariable Integer id) {
        try {
            locationService.delete(id);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
