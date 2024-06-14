package microservices.training.locations.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import microservices.training.locations.annotation.Coordinate;
import microservices.training.locations.model.Type;

@Data
public class UpdateLocationCommand {
    @Schema(description = "name of the location", example = "New York")
    private String name;
    @Schema(description = "latitude of the location", example = "42.66")
    @Coordinate(type = Type.LAT)
    private double lat;
    @Schema(description = "longitude of the location", example = "18.26")
    @Coordinate(type = Type.LON)
    private double lon;
}
