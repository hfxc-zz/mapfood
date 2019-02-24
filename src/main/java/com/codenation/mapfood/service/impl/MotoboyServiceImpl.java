package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.model.Coordinates;
import com.codenation.mapfood.model.Motoboy;
import com.codenation.mapfood.repository.MotoboyRepository;
import com.codenation.mapfood.service.MotoboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MotoboyServiceImpl implements MotoboyService {

    @Autowired
    private MotoboyRepository repository;
    private static final double degreeInMeter = 111e3D;
    private static final int minimumMotoboys = 5;

    public List<Motoboy> getAll() {
        return repository.findAll();
    }

    private List<Motoboy> getAllInRange(Coordinates coordinates, double radio) {
        List<Motoboy> motoboys = new ArrayList<>();

        double lat = coordinates.getLatitude().doubleValue();
        double lon = coordinates.getLongitude().doubleValue();
        double angle = radio / (degreeInMeter * Math.cos(lat));

        BigDecimal minLat = BigDecimal.valueOf(lat - angle);
        BigDecimal maxLat = BigDecimal.valueOf(lat + angle);
        BigDecimal minLon = BigDecimal.valueOf(lon - angle);
        BigDecimal maxLon = BigDecimal.valueOf(lon + angle);

        motoboys = repository.findByCoordinates_LongitudeBetweenAndCoordinates_LatitudeBetween(minLon, maxLon, minLat, maxLat);

        return motoboys;
    }

    private List<Motoboy> getAllInCloseRange(Coordinates coordinates) {
        /*Try to get all motoboys in 5km range, if the quantity is below a minimum value
        * expands the range using fibonnaci sequence numbers*/
        List<Motoboy> motoboys = getAllInRange(coordinates, 1e3D);

        if(motoboys.size() < minimumMotoboys) {
            motoboys = getAllInRange(coordinates, 2e3D);
        }

        if(motoboys.size() < minimumMotoboys) {
            motoboys = getAllInRange(coordinates, 3e3D);
        }

        if(motoboys.size() < minimumMotoboys) {
            motoboys = getAllInRange(coordinates, 5e3D);
        }

        if(motoboys.size() < minimumMotoboys) {
            motoboys = getAllInRange(coordinates, 8e3D);
        }

        if(motoboys.size() < minimumMotoboys) {
            motoboys = getAllInRange(coordinates, 13e3D);
        }

        return motoboys;
    }


    public Motoboy getNearest(Coordinates coordinates) {
        return getAllInCloseRange(coordinates).stream().
                min(Comparator.comparing(m -> coordinates.distanceFrom(m.getCoordinates()))).get();
    }


}
