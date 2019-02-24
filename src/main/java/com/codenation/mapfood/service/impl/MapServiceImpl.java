package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.client.*;
import com.codenation.mapfood.model.Coordinates;
import com.codenation.mapfood.service.MapService;
import com.google.maps.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private GoogleMapsClient mapsClient;

    private LatLng coordinateToLatLng(Coordinates coordinates) {
        return new LatLng(coordinates.getLatitude().doubleValue(), coordinates.getLongitude().doubleValue());
    }

    @Override
    public RouteInformation estimateTimeAndDistance(Coordinates src, List<Coordinates> dests) {
        return null;
    }

    public List<Route> getRoutes(Coordinates origin, Coordinates destination) {
        return getRoutes(origin, destination, new ArrayList<>());
    }

    public List<Route> getRoutes(Coordinates origin, Coordinates destination, List<Coordinates> stops) {
        LatLng originLatLng = coordinateToLatLng(origin);
        LatLng destLatLng = coordinateToLatLng(destination);
        List<LatLng> stopsLatLng = stops.stream().map(this::coordinateToLatLng).collect(Collectors.toList());

        DirectionsResult result = mapsClient.getDirections(Instant.now(),
                true, originLatLng, destLatLng,
                stopsLatLng.toArray(new LatLng[stopsLatLng.size()]));

        return Arrays.stream(result.routes)
                .map(Route::new)
                .collect(Collectors.toList());
    }
}
