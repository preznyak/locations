package microservices.training.locations.web;

import microservices.training.locations.model.Location;
import microservices.training.locations.service.LocationsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void getLocations() {
        when(locationsService.getLocations()).thenReturn(List.of(new Location(1L, "Debrecen", 47.54, 21.56)));

        String response = locationsController.getLocations();

        assertThat(response).startsWith("[Location { name=Debrecen");
    }

}