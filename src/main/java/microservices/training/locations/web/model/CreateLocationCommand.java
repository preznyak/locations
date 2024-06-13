package microservices.training.locations.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationCommand {

    @Schema(description = "name of the location", example = "New York")
    private String name;
    @Schema(description = "latitude of the location", example = "42.66")
    private double lat;
    @Schema(description = "longitude of the location", example = "18.26")
    private double lon;
}
