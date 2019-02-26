package com.codenation.mapfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stop_seq")
    @SequenceGenerator(name = "stop_seq", sequenceName = "stop_seq", allocationSize = 1)
    private Long id;

    @Embedded
    private Coordinates coordinates;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Orders order;

    private boolean visited;

    public Stop() {
    }

    public Stop(Coordinates coordinates) {
        this.visited = false;
        this.coordinates = coordinates;
    }

    public Stop(Coordinates coordinates, Orders order) {
        this.visited = false;
        this.order = order;
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

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if(o != null && o instanceof Stop) {
            Stop other = (Stop) o;
            if(other.getId() == this.id &&
                this.coordinates.equals(other.coordinates)) {
                return true;
            }
        }
        return false;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
