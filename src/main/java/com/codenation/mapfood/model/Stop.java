package com.codenation.mapfood.model;

import javax.persistence.*;

@Entity
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stop_seq")
    @SequenceGenerator(name = "stop_seq", sequenceName = "stop_seq", allocationSize = 1)
    private Long id;

    @Embedded
    private Coordinates coordinates;

    public Stop() {
    }

    public Stop(Coordinates coordinates) {
        this.coordinates = coordinates;
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
