package microservices.training.locations.web;

import microservices.training.locations.web.model.CreateLocationCommand;
import microservices.training.locations.web.model.LocationDto;
import microservices.training.locations.service.LocationsService;
import microservices.training.locations.web.model.QueryParameters;
import microservices.training.locations.web.model.UpdateLocationCommand;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public LocationDto findLocationById(@PathVariable("id") long id) {
        return locationsService.findLocationById(id);
    }

    @PostMapping
    public LocationDto createLocation(@RequestBody CreateLocationCommand command) {
        return locationsService.createLocation(command);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocation(
            @PathVariable("id") long id,
            @RequestBody UpdateLocationCommand command
            ) {
        return locationsService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable("id") long id) {
        locationsService.deleteLocation(id);
    }

}
