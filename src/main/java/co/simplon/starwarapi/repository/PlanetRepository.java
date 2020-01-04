package co.simplon.starwarapi.repository;

import co.simplon.starwarapi.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PlanetRepository extends JpaRepository<Planet,Long> {
}
