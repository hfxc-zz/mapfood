package com.codenation.mapfood.client;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class GoogleMapsClient {
    private static final String API_KEY = "AIzaSyAPpVqxC8fAkCEzlUFi6NR6FjVkUwlC3ws";
    private static final String LANGUAGE = "pt-BR";
    private static final GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();

    public DistanceMatrix estimateRouteTime(Instant time, Boolean isForCalculateArrivalTime,
                                            LatLng[] startingPoints, LatLng destination) {
        try {
            DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
            if (isForCalculateArrivalTime) {
                req.departureTime(time);
            } else {
                req.arrivalTime(time);
            }
            return req.origins(startingPoints)
                    .destinations(destination)
                    .mode(TravelMode.DRIVING)
                    .avoid(DirectionsApi.RouteRestriction.TOLLS)
                    .language(LANGUAGE)
                    .units(Unit.METRIC)
                    .await();

        } catch (ApiException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public DirectionsResult getDirections(Instant time, Boolean isForCalculateArrivalTime,
                                          LatLng departure, LatLng arrival, LatLng[] stops) {
        DirectionsApiRequest req = DirectionsApi.newRequest(context);
        try {
            if (isForCalculateArrivalTime) {
                req.departureTime(time);
            } else {
                req.arrivalTime(time);
            }
            return req.origin(departure).destination(arrival)
                    .mode(TravelMode.DRIVING)
                    .avoid(DirectionsApi.RouteRestriction.TOLLS)
                    .language(LANGUAGE)
                    .units(Unit.METRIC)
                    .waypoints(stops)
                    .await();

        } catch (ApiException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
