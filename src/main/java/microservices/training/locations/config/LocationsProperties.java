package microservices.training.locations.config;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@ConfigurationProperties(prefix = "locations")
@Validated
public class LocationsProperties {

    @NotNull
    private Boolean toUppercase;
}
