package com.springwebflux.service.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import com.springwebflux.model.constants.VehicleModelEnum;
import com.springwebflux.model.json.Vehicle;
import com.springwebflux.service.VehicleService;

import reactor.core.publisher.Flux;

@SpringBootTest
@AutoConfigureWebTestClient
class VehicleServiceTest {

	@Autowired
	private VehicleService vehicleService;

	@Test
	public void getVehicle_ACCENT_shouldReturnVehicle() {
		// Given

		// When
		Flux<Vehicle> vehicle = vehicleService.getVehicles(VehicleModelEnum.ACCENT);

		// Then
		assertNotNull(vehicle);
	}

	@Test
	public void getVehicle_TUCSON_shouldReturnVehicle() {
		// Given

		// When
		Flux<Vehicle> vehicle = vehicleService.getVehicles(VehicleModelEnum.TUCSON);

		// Then
		assertNotNull(vehicle);
	}

	@Test
	public void getVehicle_SANTA_FE_shouldReturnVehicle() {
		// Given

		// When
		Flux<Vehicle> vehicle = vehicleService.getVehicles(VehicleModelEnum.SANTA_FE);

		// Then
		assertNotNull(vehicle);
	}

	@Test
	public void getVehicle_GRAND_shouldReturnVehicle() {
		// Given

		// When
		Flux<Vehicle> vehicle = vehicleService.getVehicles(VehicleModelEnum.GRAND);

		// Then
		assertNotNull(vehicle);
	}

}
