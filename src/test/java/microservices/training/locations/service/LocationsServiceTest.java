package microservices.training.locations.service;

import microservices.training.locations.web.model.LocationDto;
import microservices.training.locations.web.model.QueryParameters;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LocationsServiceTest {

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService(new ModelMapper());
        List<LocationDto> locations = locationsService.listLocations(new QueryParameters());

        assertThat(locations).isNotEmpty();
        assertThat(locations).hasSize(2);

    }

}