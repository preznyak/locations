package microservices.training.locations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private Long id;
    private String name;
    private double lat;
    private double lon;

}
