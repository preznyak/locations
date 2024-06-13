package microservices.training.locations.web.model;

import lombok.Data;

@Data
public class UpdateLocationCommand {
    private String name;
    private double lat;
    private double lon;
}
