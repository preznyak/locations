package microservices.training.locations.web;

import microservices.training.locations.service.LocationsService;
import microservices.training.locations.web.model.CreateLocationCommand;
import microservices.training.locations.web.model.LocationDto;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from locations")
public class LocationsControllerRestTemplateIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    LocationsService locationsService;

    @RepeatedTest(2)
    void testListLocations() {

        LocationDto locationDto = testRestTemplate.postForObject("/api/locations",
                new CreateLocationCommand("Kiskereki", 42.56, 20.12), LocationDto.class);

        assertEquals("KISKEREKI", locationDto.getName());

        testRestTemplate.postForObject("/api/locations",
                new CreateLocationCommand("Csokaly", 45.12, 20.41), LocationDto.class);

        List<LocationDto> locations = testRestTemplate.exchange("/api/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {})
                .getBody();

        assertThat(locations)
                .extracting(LocationDto::getName)
                .containsExactly("KISKEREKI", "CSOKALY");
    }
}
