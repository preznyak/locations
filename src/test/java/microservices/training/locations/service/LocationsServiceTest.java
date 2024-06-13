package microservices.training.locations.service;

import microservices.training.locations.model.LocationDto;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class LocationsServiceTest {

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService(new ModelMapper());
        List<LocationDto> locations = locationsService.listLocations(Optional.empty());

        assertThat(locations).isNotEmpty();
        assertThat(locations).hasSize(2);

    }

}