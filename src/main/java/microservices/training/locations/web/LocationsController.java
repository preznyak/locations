package microservices.training.locations.web;

import microservices.training.locations.service.LocationsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    private LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping
    public String getLocations() {
        return locationsService.getLocations().toString();
    }

    @GetMapping("/text")
    public String getText() {
        return "Here is the text updated";
    }
}
