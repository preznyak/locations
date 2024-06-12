package microservices.training.locations.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LocationsControllerIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void getLocations() {
        String locations = locationsController.getLocations();

        assertThat(locations).isNotEmpty();
        assertThat(locations).startsWith("[Location { name=");
    }
}
