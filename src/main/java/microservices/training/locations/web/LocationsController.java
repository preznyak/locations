package microservices.training.locations.web;

import microservices.training.locations.web.model.LocationDto;
import microservices.training.locations.service.LocationsService;
import microservices.training.locations.web.model.QueryParameters;
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

}
