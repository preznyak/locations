package microservices.training.locations.web;

import microservices.training.locations.service.LocationsService;
import microservices.training.locations.web.model.LocationDto;
import microservices.training.locations.web.model.QueryParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void listLocations() {
        when(locationsService.listLocations(new QueryParameters())).thenReturn(List.of(new LocationDto(1L, "Debrecen", 47.54, 21.56)));

        List<LocationDto> response = locationsController.listLocations(new QueryParameters());

        assertThat(response).isNotEmpty();
        assertThat(response).hasSize(1);
    }

    @Test
    void findLocationById() {
        when(locationsService.findLocationById(1L)).thenReturn(new LocationDto(1L, "Nyíregyháza", 48.03, 20.41));

        LocationDto response = locationsController.findLocationById(1L);

        assertThat(response).isNotNull();
        assertThat(response).hasNoNullFieldsOrProperties();
    }

}