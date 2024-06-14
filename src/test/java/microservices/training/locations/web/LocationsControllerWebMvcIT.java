package microservices.training.locations.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.training.locations.service.LocationsService;
import microservices.training.locations.web.model.CreateLocationCommand;
import microservices.training.locations.web.model.LocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LocationsController.class)
public class LocationsControllerWebMvcIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testListLocations() throws Exception {
        when(locationsService.listLocations(any()))
                .thenReturn(List.of(
                        new LocationDto(1L, "Csokaly", 45.12, 20.41),
                        new LocationDto(2L, "Szalacs", 44.98, 21.02)
                ));

        mockMvc.perform(get("/api/locations"))
//                .andDo(print());
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo("Csokaly")));
    }

    @Test
    void testFindLocationById() throws Exception {
        when(locationsService.findLocationById(1L))
                .thenReturn(new LocationDto(1L, "Csokaly", 45.12, 20.41));

        mockMvc.perform(get("/api/locations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Csokaly")));
    }

    @Test
    void testCreateLocation() throws Exception {

        CreateLocationCommand command = new CreateLocationCommand("Kiskereki", 42.56, 20.12);
        LocationDto locationDto = new LocationDto(1L, "Kiskereki", 42.56, 20.12);
        when(locationsService.createLocation(command))
                .thenReturn(locationDto);
        String requestBody = objectMapper.writeValueAsString(command);

        mockMvc.perform(post("/api/locations").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", equalTo("Kiskereki")));
    }

    @Test
    void testCreateLocationWithInvalidCoordinate() throws Exception {

        CreateLocationCommand invalidCommand = new CreateLocationCommand("Kiskereki", 100.56, 20.12);
        LocationDto locationDto = new LocationDto(1L, "Kiskereki", 100.56, 20.12);
        when(locationsService.createLocation(invalidCommand))
                .thenReturn(locationDto);
        String invalidRequestBody = objectMapper.writeValueAsString(invalidCommand);

        mockMvc.perform(post("/api/locations").content(invalidRequestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("violations", iterableWithSize(1)))
                .andExpect(jsonPath("violations[0].field", equalTo("lat")))
                .andExpect(jsonPath("violations[0].message", equalTo("Invalid coordinate")));

    }
}
