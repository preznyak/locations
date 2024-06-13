package microservices.training.locations.service;

import microservices.training.locations.model.Location;
import microservices.training.locations.model.LocationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private final AtomicLong counter = new AtomicLong(0);

    private final ModelMapper modelMapper;

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(counter.incrementAndGet(), "Debrecen", 47.54, 21.56),
            new Location(counter.incrementAndGet(), "Siófok", 46.90, 18.07)
    )));

    public List<LocationDto> listLocations(Optional<String> prefix) {
        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
        List<Location> filtered = locations.stream()
                .filter(location -> prefix.isEmpty() || location.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);
    }

    public LocationDto findLocationById(long id) {
        return modelMapper.map(locations.stream()
                .filter(location -> location.getId() == id).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Location not found: " + id)),
                LocationDto.class);
    }
}
