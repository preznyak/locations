package microservices.training.locations.web;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import microservices.training.locations.service.LocationsService;
import microservices.training.locations.web.model.CreateLocationCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeesControllerRestAssuredIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocationsService locationsService;

    @BeforeEach
    void init() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.requestSpecification =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON);
        locationsService.deleteAllLocations();
    }

    @Test
    void testListLocations() {
        with()
                .body(new CreateLocationCommand("Chicago", 75.33, 22.65))
                .post("/api/locations")
                .then()
                .statusCode(201)
                .body("name", equalTo("Chicago"))
                .log();

        with()
                .get("/api/locations")
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("Chicago"))
                .body("size()", equalTo(1));
    }

    @Test
    void validate() {
        with()
                .body(new CreateLocationCommand("Chicago", 75.33, 22.65))
                .post("/api/locations")
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("location-dto.json"));
    }
}
