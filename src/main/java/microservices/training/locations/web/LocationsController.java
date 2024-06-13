package microservices.training.locations.web;

import microservices.training.locations.web.model.CreateLocationCommand;
import microservices.training.locations.web.model.LocationDto;
import microservices.training.locations.service.LocationsService;
import microservices.training.locations.web.model.QueryParameters;
import microservices.training.locations.web.model.UpdateLocationCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
public class LocationsController {

    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping
    public List<LocationDto> listLocations(QueryParameters queryParameters) {
        return locationsService.listLocations(queryParameters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> findLocationById(@PathVariable("id") long id) {
        try {
            LocationDto locationDto = locationsService.findLocationById(id);
            return ResponseEntity
                    .ok()
                    .header("Response-ID", UUID.randomUUID().toString())
                    .body(locationDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .notFound()
                    .header("Response-ID", UUID.randomUUID().toString())
                    .build();
        }

    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody CreateLocationCommand command,
                                                      UriComponentsBuilder uri) {
        LocationDto locationDto = locationsService.createLocation(command);
        return ResponseEntity
                .created(uri.path("/api/locations/{id}").buildAndExpand(locationDto.getId()).toUri())
                .body(locationDto);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocation(
            @PathVariable("id") long id,
            @RequestBody UpdateLocationCommand command
            ) {
        return locationsService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") long id) {
        locationsService.deleteLocation(id);
    }

}
