package com.codenation.mapfood.model;
import javax.persistence.*;

@Entity
public class Motoboy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motoboy_seq")
    @SequenceGenerator(name = "motoboy_seq", sequenceName = "motoboy_seq", allocationSize = 1)
    private Long id;

    @Embedded
    private Coordinates coordinates;

    public Motoboy() {
    }

    public Motoboy(Long id, String longitude, String latitude) {
        this.id = id;
        this.coordinates = new Coordinates(longitude, latitude);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}
