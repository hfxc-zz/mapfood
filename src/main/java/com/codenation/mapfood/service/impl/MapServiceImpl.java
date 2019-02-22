package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.client.GoogleMapsClient;
import com.codenation.mapfood.client.RouteInformation;
import com.codenation.mapfood.model.Coordinates;
import com.codenation.mapfood.service.MapService;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
        Instant departureTime = Instant.now();
        LatLng departureLocation = coordinateToLatLng(src);
        List<LatLng> arrivalLocations = dests.stream()
                                             .map(c -> coordinateToLatLng(c))
                                             .collect(Collectors.toList());

        LatLng[] arrivals = (LatLng[]) arrivalLocations.toArray();
        DistanceMatrix matrix = mapsClient.estimateRouteTime(departureTime, true, departureLocation, arrivals);

        RouteInformation result = new RouteInformation();
        result.setSrc(departureLocation);
        result.setDests(arrivalLocations);
        result.setDuration(matrix.rows[0].elements[0].duration);
        result.setDistance(matrix.rows[0].elements[0].distance);
        return result;
    }
}
