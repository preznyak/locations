package microservices.training.locations.web;

import microservices.training.locations.model.LocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LocationsControllerIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void getLocations() {
        List<LocationDto> locations = locationsController.listLocations(Optional.empty());

        assertThat(locations).isNotEmpty();
        assertThat(locations).hasSize(2);
    }
}
