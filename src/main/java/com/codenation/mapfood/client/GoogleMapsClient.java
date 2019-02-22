package com.codenation.mapfood.client;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class GoogleMapsClient {
    private static final String API_KEY = "AIzaSyAPpVqxC8fAkCEzlUFi6NR6FjVkUwlC3ws";
    private static final String LANGUAGE = "pt-BR";
    private static final GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();

    public DistanceMatrix estimateRouteTime(Instant time, Boolean isForCalculateArrivalTime,
                                            LatLng departure, LatLng... arrivals) {
        try {
            DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
            if (isForCalculateArrivalTime) {
                req.departureTime(time);
            } else {
                req.arrivalTime(time);
            }
            return req.origins(departure)
                    .destinations(arrivals)
                    .mode(TravelMode.DRIVING)
                    .avoid(DirectionsApi.RouteRestriction.TOLLS)
                    .language(LANGUAGE)
                    .await();

        } catch (ApiException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public DirectionsResult getDirections(Instant time, Boolean isForCalculateArrivalTime,
                              LatLng departure, LatLng arrival) {
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
                    .await();

        } catch (ApiException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
