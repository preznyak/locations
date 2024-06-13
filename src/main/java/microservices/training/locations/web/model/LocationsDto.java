package microservices.training.locations.web.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*
This wrapper class will not be used in the future.
It was created only for learning purposes (xml as response)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "locations")
public class LocationsDto {

    private List<LocationDto> locations;
}
