package ge.conditery.oldTaste.service.implementation;

import ge.conditery.oldTaste.enums.AppErrorCode;
import ge.conditery.oldTaste.model.Location;
import ge.conditery.oldTaste.repository.LocationRepository;
import ge.conditery.oldTaste.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;


    @Override
    public Location create(Location location) {
        log.info("Saving new location: " + location.getAddress());
        return locationRepository.save(location);
    }

    @Override
    public Collection<Location> locationList(int limit) {
        return null;
    }

    @Override
    public Location get(int id) {
        log.info("Find location by Id: " + id);
        return locationRepository.findById(id).get();
    }

    @Override
    public Location update(Location location) {
        log.info("Update location: " + location.getAddress());
        return locationRepository.save(location);
    }

    @Override
    public Boolean delete(int id) {
        log.info("Delete location: " + id);
        try {
            locationRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Unable to delete " + AppErrorCode.LOCATION_NOT_EXIST);
        }

        return TRUE;
    }
}
