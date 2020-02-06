package co.simplon.starwarapi.controller;

import co.simplon.starwarapi.model.Planet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.simplon.starwarapi.repository.PlanetRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.HTML;
import javax.swing.text.html.Option;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "API pour les opérations CRUD sur les planetes.")
@RestController
@RequestMapping("/api/planets")
public class PlanetController {
    private PlanetRepository planetRepository;

    public PlanetController(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @ApiOperation(value = "Récupère la liste des planetes au format JSON")
    @GetMapping("/list")
    public ResponseEntity<List<Planet>> listPlanets(){
        List<Planet> response;
        response = planetRepository.findAll();
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Créer une planete en BD")
    @PostMapping("/create")
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planetToCreate) {
          Planet planet = planetRepository.save(planetToCreate);
          return ResponseEntity.ok(planet);
          // TODO Fix to generate a return code 'CREATED' instead of 'OK'
//        planetRepository.save(planet);
//          URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("{id}")
//                .buildAndExpand(planet.getId())
//                .toUri();
//         return ResponseEntity.created(location).build();

    }

    @ApiOperation(value = "Récupère une planete grâce à son ID")
    @GetMapping("/{planetId}")
    public ResponseEntity<Planet> getOne(@PathVariable Long planetId) {
        Optional<Planet> planet = planetRepository.findById(planetId);
        if (planet.isPresent()) {
            return ResponseEntity.ok(planetRepository.findById(planetId).get());
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Mettre à jour une planete grâce à son ID")
    @PutMapping("/update/{planetId}")
    public ResponseEntity<Planet> updatePlanet(@RequestBody Planet planet,@PathVariable Long planetId){
        if (planetRepository.existsById(planetId) && planet.getId().equals(planetId)){
            return ResponseEntity.ok(planetRepository.save(planet));
        }
        else return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Supprimer une planete en BD à partir de son ID")
    @DeleteMapping("/delete/{planetId}")
    public ResponseEntity<Planet> deletePlanet(@PathVariable Long planetId){
        if (planetRepository.existsById(planetId)){
            planetRepository.deleteById(planetId);
        }
        return ResponseEntity.noContent().build();
    }
}


