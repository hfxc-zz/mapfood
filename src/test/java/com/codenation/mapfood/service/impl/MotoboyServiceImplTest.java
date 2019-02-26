package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.exception.NoMotoboyInRangeException;
import com.codenation.mapfood.model.Coordinates;
import com.codenation.mapfood.model.Motoboy;
import com.codenation.mapfood.repository.MotoboyRepository;
import com.codenation.mapfood.service.MotoboyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
public class MotoboyServiceImplTest {
    @TestConfiguration
    static class DeliveryServiceImplTestContextConfiguration {

        @Bean
        public MotoboyService motoboyService() {
            return new MotoboyServiceImpl();
        }
    }

    @Autowired
    private MotoboyService motoboyService;

    @MockBean
    private MotoboyRepository motoboyRepository;

    @Before
    public void setupRepositoryMock() {
        Motoboy motoboy1 = new Motoboy(1L, "-51.216203", "-30.07518676");
        Motoboy motoboy2 = new Motoboy(2L, "-51.142207", "-30.08580672");
        Motoboy motoboy3 = new Motoboy(3L, "-51.14220035", "-30.03306622");
        Motoboy motoboy4 = new Motoboy(3L, "-51.15050323", "-30.10878061");
        Motoboy motoboy5 = new Motoboy(3L, "-51.20737712", "-30.04466086");

        Mockito.when(motoboyRepository.findAll()).thenReturn(Arrays.asList(motoboy1, motoboy2, motoboy3, motoboy4, motoboy5));
        Mockito.when(motoboyRepository.findByCoordinates_LongitudeBetweenAndCoordinates_LatitudeBetween(
                -51.1800028576246, -51.1044111423754, -30.123602577624602, -30.0480108623754))
                .thenReturn(Arrays.asList(motoboy1, motoboy2, motoboy3, motoboy4, motoboy5));
    }

    @Test
    public void shouldReturnAllMotoboys() {
        List<Motoboy> list = motoboyService.getAll();

        assertThat(list, hasSize(5));
    }

    @Test
    public void shouldReturnTheNearestMotoboy() throws NoMotoboyInRangeException {
        Motoboy motoboy = motoboyService.getNearest(new Coordinates("-51.142207", "-30.08580672"));

        assertThat(motoboy.getId(), is(2L));
    }

    @Test(expected = NoMotoboyInRangeException.class)
    public void shouldNotFindMotoboyInRange() throws NoMotoboyInRangeException {
        Motoboy motoboy = motoboyService.getNearest(new Coordinates("-51.942207", "-30.98580672"));
    }
}
