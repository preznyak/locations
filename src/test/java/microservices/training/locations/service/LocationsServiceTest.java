package microservices.training.locations.service;

import microservices.training.locations.config.LocationsProperties;
import microservices.training.locations.model.Location;
import microservices.training.locations.repo.LocationsRepository;
import microservices.training.locations.web.mapper.LocationMapper;
import microservices.training.locations.web.model.LocationDto;
import microservices.training.locations.web.model.QueryParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class LocationsServiceTest {

    @Mock
    LocationsRepository locationsRepository;

    @Mock
    LocationsProperties locationsProperties;

    @Mock
    LocationMapper locationMapper;

    @InjectMocks
    LocationsService locationsService;

    @Test
    void getLocations() {
        doReturn(getLocationList()).when(locationsRepository).findAll();
        doReturn(getLocationDtoList()).when(locationMapper).toDto(getLocationList());

        List<LocationDto> locations = locationsService.listLocations(new QueryParameters());

        assertThat(locations).isNotEmpty();
        assertThat(locations).hasSize(2);

    }

    private List<Location> getLocationList() {
        return List.of(
                new Location(1L, "WASHINGTON", 47.21, 100.47),
                new Location(2L, "L.A.", 40.01, 100.47)
        );
    }

    private List<LocationDto> getLocationDtoList() {
        return List.of(
                new LocationDto(1L, "WASHINGTON", 47.21, 100.47),
                new LocationDto(2L, "L.A.", 40.01, 100.47)
        );
    }

}