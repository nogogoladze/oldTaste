package ge.conditery.oldTaste.service;

import ge.conditery.oldTaste.model.Location;

import java.util.Collection;

public interface LocationService {
    Location create(Location location);

    Collection<Location> locationList(int limit);

    Location get(int id);

    Location update(Location location);

    Boolean delete(int id);
}
