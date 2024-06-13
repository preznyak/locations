package microservices.training.locations.web.model;

import lombok.Data;

@Data
public class QueryParameters {
    private String prefix;
    private String suffix;
}
