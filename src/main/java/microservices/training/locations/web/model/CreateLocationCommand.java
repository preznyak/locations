package microservices.training.locations.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.training.locations.annotation.Coordinate;
import microservices.training.locations.model.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationCommand {

    @Schema(description = "name of the location", example = "New York")
    @NotBlank(message = "Name can not be blank.")
    private String name;

    @Schema(description = "latitude of the location", example = "42.66")
    @Coordinate(type = Type.LAT)
    private double lat;

    @Schema(description = "longitude of the location", example = "18.26")
    @Coordinate(type = Type.LON)
    private double lon;
}
