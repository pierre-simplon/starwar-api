package co.simplon.starwarapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StarWarApiApplicationTests {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads(){

    }

    // Test get planets list
    @Test
    public void getPlanets() {
        // When retrieving planets from /api/planets/list
        List<?> planets = this.restTemplate.getForObject("/api/planets/list", List.class);

        // Then a non null list should be returned
        assertThat(planets).isNotNull();
    }

    @Test
    public void getPlanetList() {
        // When retrieving planets from /api/planets/list
        ResponseEntity <List> responseEntity = this.restTemplate.exchange("/api/planets/list", HttpMethod.GET,null,List.class);
        List<?> planets = responseEntity.getBody();

        // Then a non null list should be returned and a 200 response code
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(planets).isNotNull();
    }
}
