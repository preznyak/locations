package microservices.training.locations.web;

import microservices.training.locations.service.LocationsService;
import microservices.training.locations.web.model.CreateLocationCommand;
import microservices.training.locations.web.model.LocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationsControllerWebClientIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testCreateLocation() {
        when(locationsService.createLocation(any()))
                .thenReturn(new LocationDto(1L, "San Francisco", 78.25, 48.25));

        webTestClient
                .post()
                .uri("/api/locations")
                .bodyValue(new CreateLocationCommand("San Francisco", 78.25, 48.25))
                .exchange()
                .expectStatus().isCreated()
//                .expectBody(String.class).value(s -> System.out.println(s));
//                .expectBody().jsonPath("name").isEqualTo("San Francisco");
                .expectBody(LocationDto.class).value(l -> assertEquals("San Francisco", l.getName()));
    }

    @Test
    void testListLocations() {
        when(locationsService.listLocations(any()))
                .thenReturn(List.of(
                        new LocationDto(1L, "Debrecen", 47.54, 21.56),
                        new LocationDto(2L, "Miskolc", 44.84, 20.82)
                ));

        webTestClient
                .get()
//                .uri("/api/locations")
                .uri(builder -> builder.path("/api/locations").queryParam("prefix", "De").build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(LocationDto.class).hasSize(2).contains(
                        new LocationDto(1L, "Debrecen", 47.54, 21.56)
                );

    }
}
