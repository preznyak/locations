package microservices.training.locations.service;

import microservices.training.locations.exception.LocationNotFoundException;
import microservices.training.locations.web.mapper.LocationMapper;
import microservices.training.locations.model.Location;
import microservices.training.locations.web.model.CreateLocationCommand;
import microservices.training.locations.web.model.LocationDto;
import microservices.training.locations.web.model.QueryParameters;
import microservices.training.locations.web.model.UpdateLocationCommand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private final AtomicLong idGenerator = new AtomicLong();

    private final LocationMapper locationMapper;

    public LocationsService(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    private final List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(idGenerator.incrementAndGet(), "Debrecen", 47.54, 21.56),
            new Location(idGenerator.incrementAndGet(), "Siófok", 46.90, 18.07)
    )));

    public List<LocationDto> listLocations(QueryParameters queryParameters) {
        List<Location> filtered = locations.stream()
                .filter(location -> queryParameters.getPrefix() == null || location.getName().toLowerCase().startsWith(queryParameters.getPrefix().toLowerCase()))
                .filter(location -> queryParameters.getSuffix() == null || location.getName().toLowerCase().endsWith(queryParameters.getSuffix().toLowerCase()))
                .collect(Collectors.toList());
        return locationMapper.toDto(filtered);
    }

    public LocationDto findLocationById(long id) {
        return locationMapper.toDto(locations.stream()
                .filter(location -> location.getId() == id).findAny()
                .orElseThrow(notFoundException(id)));
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(idGenerator.incrementAndGet(), command.getName(), command.getLat(), command.getLon());
        locations.add(location);
        return locationMapper.toDto(location);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {

        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst().orElseThrow(notFoundException(id));

        location.setName(command.getName());
        location.setLat(command.getLat());
        location.setLon(command.getLon());

        return locationMapper.toDto(location);
    }

    public void deleteLocation(long id) {
        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst().orElseThrow(notFoundException(id));

        locations.remove(location);
    }

    private static Supplier<LocationNotFoundException> notFoundException(long id) {
        return () -> new LocationNotFoundException("Location not found: %d".formatted(id));
    }
}
