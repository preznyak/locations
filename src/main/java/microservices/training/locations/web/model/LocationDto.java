package microservices.training.locations.web.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "locationDto")
public class LocationDto {
    private Long id;
    private String name;
    private double lat;
    private double lon;
}
