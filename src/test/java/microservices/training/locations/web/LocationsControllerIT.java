package microservices.training.locations.web;

import microservices.training.locations.web.model.LocationDto;
import microservices.training.locations.web.model.QueryParameters;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LocationsControllerIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void getLocations() {
        List<LocationDto> locations = locationsController.listLocations(new QueryParameters());

        assertThat(locations).isNotEmpty();
        assertThat(locations).hasSize(2);
    }
}
