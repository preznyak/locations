package microservices.training.locations.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.training.locations.config.LocationsProperties;
import microservices.training.locations.exception.LocationNotFoundException;
import microservices.training.locations.repo.LocationsRepository;
import microservices.training.locations.web.mapper.LocationMapper;
import microservices.training.locations.model.Location;
import microservices.training.locations.web.model.CreateLocationCommand;
import microservices.training.locations.web.model.LocationDto;
import microservices.training.locations.web.model.QueryParameters;
import microservices.training.locations.web.model.UpdateLocationCommand;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(LocationsProperties.class)
@Slf4j
public class LocationsService {

    private AtomicLong idGenerator = new AtomicLong();

    private final LocationMapper locationMapper;
    private final LocationsProperties locationsProperties;
    private final LocationsRepository locationsRepository;

    public List<LocationDto> listLocations(QueryParameters queryParameters) {
        List<Location> filtered = locationsRepository.findAll().stream()
                .filter(location -> queryParameters.getPrefix() == null || location.getName().toLowerCase().startsWith(queryParameters.getPrefix().toLowerCase()))
                .filter(location -> queryParameters.getSuffix() == null || location.getName().toLowerCase().endsWith(queryParameters.getSuffix().toLowerCase()))
                .collect(Collectors.toList());
        log.debug("Location list has been sent with size {}", filtered.size());
        return locationMapper.toDto(filtered);
    }

    public LocationDto findLocationById(long id) {
        LocationDto locationDto = locationMapper.toDto(locationsRepository.findById(id)
                .orElseThrow(notFoundException(id)));
        log.debug("Location has been found with id {}", id);
        return locationDto;
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        String locationName = locationsProperties.getToUppercase() ? command.getName().toUpperCase() : command.getName();
        Location location = new Location(idGenerator.incrementAndGet(), locationName, command.getLat(), command.getLon());
        locationsRepository.save(location);
        log.info("Location has been created.");
        log.debug("Location has been created with name {} and id {}", locationName, location.getId());
        return locationMapper.toDto(location);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {

        Location location = locationsRepository.findById(id)
                .orElseThrow(notFoundException(id));
        String locationName = locationsProperties.getToUppercase() ? command.getName().toUpperCase() : command.getName();
        location.setName(locationName);
        location.setLat(command.getLat());
        location.setLon(command.getLon());

        locationsRepository.save(location);
        log.debug("Location has been updated with id {}", location.getId());

        return locationMapper.toDto(location);
    }

    public void deleteLocation(long id) {
        locationsRepository.deleteById(id);
        log.debug("Location has been removed with id {}", id);
    }

    private static Supplier<LocationNotFoundException> notFoundException(long id) {
        return () -> new LocationNotFoundException("Location not found: %d".formatted(id));
    }

    public void deleteAllLocations() {
        locationsRepository.deleteAll();
    }
}
