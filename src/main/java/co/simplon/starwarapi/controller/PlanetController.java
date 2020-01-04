package co.simplon.starwarapi.controller;

import co.simplon.starwarapi.model.Planet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.simplon.starwarapi.repository.PlanetRepository;

import javax.swing.text.html.HTML;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planets")
public class PlanetController {
    private PlanetRepository planetRepository;

    public PlanetController(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Planet>> listPlanets(){
        List<Planet> response;
        response = planetRepository.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public void addPlanet(@RequestBody Planet planet) {
        planetRepository.save(planet);
    }

    @GetMapping("/{planetId}")
    public ResponseEntity<Planet> getOne(@PathVariable Long planetId) {
        return ResponseEntity.ok(planetRepository.findById(planetId).get());
    }

    @PutMapping("/update/{planetId}")
    public ResponseEntity<Planet> updatePlanet(@RequestBody Planet planet,@PathVariable Long planetId){
        if (planetRepository.existsById(planetId) && planet.getId().equals(planetId)){
            return ResponseEntity.ok(planetRepository.save(planet));
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{planetId}")
    public ResponseEntity<Planet> deletePlanet(@PathVariable Long planetId){
        if (planetRepository.existsById(planetId)){
            planetRepository.deleteById(planetId);
        }
        return ResponseEntity.noContent().build();
    }
}


