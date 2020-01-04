package co.simplon.starwarapi.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "planets")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "planet_generator",
//    sequenceName = "planet_id_seq",
//    allocationSize = 1)
    private Long id;

    @Column (name = "planet_name")
    private String planetName;

    @Column (name = "rotation_period")
    private Integer rotationPeriod;

    @Column (name = "orbital_period")
    private Integer orbitalPeriod;

    @Column (name = "diameter")
    private Integer diameter;

    @Column (name = "gravity")
    private Integer gravity;

    @Column (name = "surface_water")
    private Integer surfaceWater;

    @Column (name = "population")
    private Long population;

    public Planet(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public Integer getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(Integer rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public Integer getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(Integer orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    public Integer getGravity() {
        return gravity;
    }

    public void setGravity(Integer gravity) {
        this.gravity = gravity;
    }

    public Integer getSurfaceWater() {
        return surfaceWater;
    }

    public void setSurfaceWater(Integer surfaceWater) {
        this.surfaceWater = surfaceWater;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}
