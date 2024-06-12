package microservices.training.locations.service;

import microservices.training.locations.model.Location;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LocationsServiceTest {

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService();
        List<Location> locations = locationsService.getLocations();

        assertThat(locations).isNotEmpty();
        assertThat(locations).hasSize(3);

    }

}