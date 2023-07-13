package com.springwebflux.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.springwebflux.model.constants.VehicleModelEnum;
import com.springwebflux.model.json.Vehicle;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class VehicleService {

	private final WebClient webClientVehicles;

	public Flux<Vehicle> getVehicles(VehicleModelEnum modelo) {
		String URI = findUriByModel(modelo);
		return webClientVehicles.get().uri(URI).accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Vehicle.class);
	}

	// Vehicles URLs
	static String VEHICLES_INFO_BASE_URL = "https://kerner.hyundai.com.ec";
	static String VEHICLES_ACCENT_URL = "/api/versiones/1/1036";
	static String VEHICLES_TUCSON_URL = "/api/versiones/1/1031";
	static String VEHICLES_SANTA_FE_URL = "/api/versiones/1/1023";
	static String VEHICLES_GRAND_URL = "/api/versiones/1/1038";

	private String findUriByModel(VehicleModelEnum modelo) {

		switch (modelo) {
		case ACCENT:
			return VEHICLES_ACCENT_URL;
		case TUCSON:
			return VEHICLES_TUCSON_URL;
		case SANTA_FE:
			return VEHICLES_SANTA_FE_URL;
		case GRAND:
			return VEHICLES_GRAND_URL;
		default:
			// TODO: Error handling
			break;
		}
		return null;
	}
}
