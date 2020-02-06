package co.simplon.starwarapi;

import co.simplon.starwarapi.model.Planet;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private Long lastCreatedPlanetId = 0L;

    // Get list of planets
    @Test
    @Order(1)
    public void getListOfPlanets() {
        // When getting on /api/planets/
        ResponseEntity<List> responseEntity = this.restTemplate.exchange("/api/planets/list", HttpMethod.GET, null, List.class);
        List<?> planets = responseEntity.getBody();

        // Then OK status code should be sent back and
        // the list of planets should be returned and should filled.
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(planets).isNotNull();
    }

    // Get existing planet
    @Test
    @Order(2)
    public void getOneExistingPlanet() {
        // When getting on /api/planets/1
        ResponseEntity<Planet> responseEntity = this.restTemplate.exchange("/api/planets/{planetId}", HttpMethod.GET, null, Planet.class, 1);
        Planet planet = responseEntity.getBody();

        // Then OK status code should be sent back
        // And id should be equal to url param and name should be filled
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(planet).isNotNull();
        assertThat(planet.getId()).isEqualTo(1L);
        assertThat(planet.getPlanetName()).isNotEmpty();
    }

    // Get non existing planet
    @Test
    @Order(3)
    public void getNonExistingPlanet() {
        // When getting on /api/planets/2000
        ResponseEntity<Planet> responseEntity = this.restTemplate.exchange("/api/planets/{planetId}", HttpMethod.GET, null, Planet.class, 2000);

        // Then NOT_FOUND status code should be sent back
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    // Create new planet
    @Test
    @Order(4)
    public void createNewPlanet() {
        // Given a new valid Planet
        String planetName = "Terre";
        Planet newPlanet = new Planet(planetName);

        HttpEntity<Planet> planetHttpEntity = new HttpEntity<>(newPlanet, null);

        // When posting this new planet to /api/planet/create
        ResponseEntity<Planet> responseEntity = this.restTemplate.postForEntity("/api/planets/create", planetHttpEntity, Planet.class);
        Planet createdPlanet = responseEntity.getBody();

        // Then OK status code should be sent back and
        // the created planet should be returned and should be have an ID and name should be set.
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(createdPlanet.getId()).isPositive();
        assertThat(createdPlanet.getPlanetName()).isEqualTo(planetName);

        lastCreatedPlanetId = createdPlanet.getId();
    }

    // Update existing planet
    @Test
    @Order(5)
    public void updateExistingPlanet() {
        // Given an existing valid Planet (with name modified)
        String newName = "Alderaaaaaaaaaan";
        Planet existingPlanet = new Planet(1L, newName);

        HttpEntity<Planet> planetHttpEntity = new HttpEntity<>(existingPlanet, null);

        // When putting this existing planet to /api/planet/update/1
        ResponseEntity<Planet> responseEntity = this.restTemplate.exchange("/api/planets/update/{planetId}", HttpMethod.PUT, planetHttpEntity, Planet.class, 1);
        Planet updatedPlanet = responseEntity.getBody();

        // Then OK status code should be sent back and
        // the updated planet should be returned and should have the same ID and an updated name.
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updatedPlanet.getId()).isEqualTo(1L);
        assertThat(updatedPlanet.getPlanetName()).isEqualTo(newName);
    }

    // Update non existing planet
    @Test
    @Order(6)
    public void updateNonExistingPlanet() {
        // Given a non existing Planet
        String planetName = "NonExistingPlanet";
        Planet nonExistingPlanet = new Planet(2000L, planetName);

        HttpEntity<Planet> planetHttpEntity = new HttpEntity<>(nonExistingPlanet, null);

        // When putting this planet to /api/planet/update/2000
        ResponseEntity<Planet> responseEntity = this.restTemplate.exchange("/api/planets/update/{planetId}", HttpMethod.PUT, planetHttpEntity, Planet.class, 2000L);

        // Then NOT_FOUND status code should be sent back.
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    // Delete planet
    @Test
    @Order(7)
    public void deletePlanet() {
        // When deleting to /api/planet/62
        ResponseEntity<Planet> responseEntity = this.restTemplate.exchange("/api/planets/delete/{planetId}", HttpMethod.DELETE, null, Planet.class, lastCreatedPlanetId);

        // Then NO_CONTENT status code should be sent back.
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}