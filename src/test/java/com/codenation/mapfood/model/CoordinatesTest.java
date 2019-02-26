package com.codenation.mapfood.model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CoordinatesTest {

    @Test
    public void shouldCalculateCorrectDistanceBetweenTwoCoordinates() {
        Coordinates coordinate1 = new Coordinates("-51.228496", "-30.03742831");
        Coordinates coordinate2 = new Coordinates("-51.136677", "-30.07824631");
        assertEquals(9934.24D, coordinate1.distanceFrom(coordinate2), 0.01D);
    }

}
