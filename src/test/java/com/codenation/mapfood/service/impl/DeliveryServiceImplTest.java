package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.client.GoogleMapsClient;
import com.codenation.mapfood.model.*;
import com.codenation.mapfood.repository.DeliveryRepository;
import com.codenation.mapfood.service.DeliveryService;
import com.codenation.mapfood.service.MapService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
public class DeliveryServiceImplTest {
	@TestConfiguration
	static class DeliveryServiceImplTestContextConfiguration {

		@Bean
		public GoogleMapsClient mapsClient() { return new GoogleMapsClient(); }

		@Bean
		public MapService mapService() { return new MapServiceImpl(); }

		@Bean
		public DeliveryService deliveryService() {
			return new DeliveryServiceImpl();
		}
	}

	@Autowired
	private DeliveryService deliveryService;

	@MockBean
	private DeliveryRepository deliveryRepository;

	@Before
	public void setupRepositoryMock() {
		Motoboy motoboy = new Motoboy(1L, "-51.216203", "-30.07518676");
		Restaurant restaurant = new Restaurant(1L, new Coordinates("-51.194512","-30.038112"), "Wood Pizzas", "Pizza", new City(0L, "PORTO ALEGRE"));
		String status = "IN PROGRESS";

		Customer customer = new Customer(1L, "-51.228496","-30.03742831");

		Delivery delivery = new Delivery(motoboy, restaurant, status);
		delivery.setId(1L);

		Orders order = new Orders();
		order.setRestaurant(restaurant);
		order.setDelivery(delivery);
		order.setCustomer(customer);
		order.setCreationDate(LocalDateTime.now());

		delivery.setOrders(Arrays.asList(order));

		Mockito.when(deliveryRepository.findByRestaurantIdAndStatus(restaurant.getId(), "IN PROGRESS")).thenReturn(Arrays.asList(delivery));
	}

	@Test
	public void shouldReturnDeliveryWithStatusInProgress() {
		//ACTION
		List<Delivery> list = deliveryService.getRestaurantInProgressOrders(new Restaurant(1L));

		assertThat(list, hasSize(1));
		assertThat(list.get(0).getId(), is(1L));
	}

	@Test
	public void shouldNotReturnDelivery() {
		//ACTION
		List<Delivery> list = deliveryService.getRestaurantInProgressOrders(new Restaurant(2L));

		assertThat(list, is(empty()));
	}

	@Test
	public void shouldReturnADeliveryAvaliableToOrder() {
		Orders order = new Orders();
		order.setRestaurant(new Restaurant(1L));
		order.setCustomer(new Customer(2L, "-51.19373482", "-30.048995"));
		order.setCreationDate(LocalDateTime.now());

		//ACTION
		Delivery delivery = deliveryService.orderCanBeAddedToAnExistingDelivery(order);

		assertThat(delivery, is(notNullValue()));
		assertThat(delivery.getId(), is(1L));
	}

	@Test
	public void shouldNotReturnADeliveryAvaliableToOrderFarAway() {
		Orders order = new Orders();
		order.setRestaurant(new Restaurant(1L));
		order.setCustomer(new Customer(2L, "-51.1461471", "-30.09126215"));
		order.setCreationDate(LocalDateTime.now());
		//ACTION
		Delivery delivery = deliveryService.orderCanBeAddedToAnExistingDelivery(order);

		assertThat(delivery, is(nullValue()));
	}
}
