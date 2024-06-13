package microservices.training.locations.web.mapper;

import microservices.training.locations.model.Location;
import microservices.training.locations.web.model.LocationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto toDto(Location location);

    List<LocationDto> toDto(List<Location> locations);
}
