package microservices.training.locations.service;

import microservices.training.locations.model.Location;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private List<Location> locations = new ArrayList<>();

    public List<Location> getLocations() {
        if (locations.isEmpty()) {
            fillLocationsList();
        }
        return locations;
    }

    private void fillLocationsList() {
        locations.add(new Location(1L, "Debrecen", 47.54, 21.56));
        locations.add(new Location(2L, "Siófok", 46.90, 18.07));
        locations.add(new Location(3L, "Répáshuta", 48.04, 20.51));
    }
}
