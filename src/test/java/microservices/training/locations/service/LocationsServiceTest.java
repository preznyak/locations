package microservices.training.locations.service;

import microservices.training.locations.web.mapper.LocationMapper;
import microservices.training.locations.web.model.LocationDto;
import microservices.training.locations.web.model.QueryParameters;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LocationsServiceTest {

    LocationMapper locationMapper = Mappers.getMapper(LocationMapper.class);

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService(locationMapper);
        List<LocationDto> locations = locationsService.listLocations(new QueryParameters());

        assertThat(locations).isNotEmpty();
        assertThat(locations).hasSize(2);

    }

}