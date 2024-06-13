package microservices.training.locations.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import microservices.training.locations.web.model.*;
import microservices.training.locations.service.LocationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@Tag(name = "Operations on locations")
public class LocationsController {

    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping
    @Operation(summary = "list all location")
    @ApiResponse(responseCode = "200", description = "locations has been found")
    public List<LocationDto> listLocations(QueryParameters queryParameters) {
        return locationsService.listLocations(queryParameters);
    }

    @GetMapping("/{id}")
    @Operation(summary = "find a location by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "location has been found with the given id"),
            @ApiResponse(responseCode = "404", description = "location not found with the given id")
    })
    public LocationDto findLocationById(
            @Parameter(description = "Id of the location", example = "1")
            @PathVariable("id") long id
    ) {
        return locationsService.findLocationById(id);
    }

    @PostMapping
    @Operation(summary = "creates a location")
    @ApiResponse(responseCode = "201", description = "location has been created")
    public ResponseEntity<LocationDto> createLocation(
            @RequestBody CreateLocationCommand command,
            UriComponentsBuilder uri
    ) {
        LocationDto locationDto = locationsService.createLocation(command);
        return ResponseEntity
                .created(uri.path("/api/locations/{id}").buildAndExpand(locationDto.getId()).toUri())
                .body(locationDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "updates a location")
    @ApiResponse(responseCode = "200", description = "location has been updated")
    public LocationDto updateLocation(
            @Parameter(description = "Id of the location", example = "1") @PathVariable("id") long id,
            @RequestBody UpdateLocationCommand command
    ) {
        return locationsService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "deletes a location")
    @ApiResponse(responseCode = "204", description = "location has been deleted")
    public void deleteLocation(
            @Parameter(description = "Id of the location", example = "1")
            @PathVariable("id") long id
    ) {
        locationsService.deleteLocation(id);
    }

}
