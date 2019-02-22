package com.codenation.mapfood.service;

import com.codenation.mapfood.client.RouteInformation;
import com.codenation.mapfood.model.Coordinates;

import java.util.List;

public interface MapService {
    RouteInformation estimateTimeAndDistance(Coordinates src, List<Coordinates> dests);
}
