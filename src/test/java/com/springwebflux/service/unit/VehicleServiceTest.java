package com.springwebflux.service.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.springwebflux.model.constants.VehicleModelEnum;
import com.springwebflux.model.json.Vehicle;
import com.springwebflux.service.VehicleService;

import reactor.core.publisher.Flux;

@SpringBootTest
@AutoConfigureWebTestClient
class VehicleServiceTest {

	@MockBean(name = "webClientVehicle")
	private WebClient webClientVehicle;

	private VehicleService vehicleService;

	@BeforeEach
	public void init() {
		webClientVehicle = Mockito.mock(WebClient.class);
		vehicleService = new VehicleService(webClientVehicle);
	}

	@Test
	public void getVehicle_ACCENT_shouldReturnVehicle() {
		// Given
		final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
		final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
		final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

		when(webClientVehicle.get()).thenReturn(uriSpecMock);
		when(uriSpecMock.uri("/api/versiones/1/1036")).thenReturn(headersSpecMock);
		when(headersSpecMock.accept(MediaType.APPLICATION_JSON)).thenReturn(headersSpecMock);
		when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);

		Vehicle vehicleObj = new Vehicle(1036L, "ALL NEW ACCENT L", 2023, 18990.0, 0.0, 18990.0, 0, "40");

		when(responseSpecMock.bodyToFlux(Vehicle.class)).thenReturn(Flux.just(vehicleObj));

		// When
		Flux<Vehicle> vehicle = vehicleService.getVehicles(VehicleModelEnum.ACCENT);

		// Then
		assertNotNull(vehicle);
	}

	@Test
	public void getVehicle_TUCSON_shouldReturnVehicle() {
		// Given
		final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
		final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
		final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

		when(webClientVehicle.get()).thenReturn(uriSpecMock);
		when(uriSpecMock.uri("/api/versiones/1/1031")).thenReturn(headersSpecMock);
		when(headersSpecMock.accept(MediaType.APPLICATION_JSON)).thenReturn(headersSpecMock);
		when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);

		Vehicle vehicleObj = new Vehicle(2019L, "ALL NEW TUCSON TM  GL", 2023, 34990.0, 0.0, 34990.0, 0, "11");

		when(responseSpecMock.bodyToFlux(Vehicle.class)).thenReturn(Flux.just(vehicleObj));

		// When
		Flux<Vehicle> vehicle = vehicleService.getVehicles(VehicleModelEnum.TUCSON);

		// Then
		assertNotNull(vehicle);
	}

	@Test
	public void getVehicle_SANTA_FE_shouldReturnVehicle() {
		// Given
		final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
		final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
		final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

		when(webClientVehicle.get()).thenReturn(uriSpecMock);
		when(uriSpecMock.uri("/api/versiones/1/1023")).thenReturn(headersSpecMock);
		when(headersSpecMock.accept(MediaType.APPLICATION_JSON)).thenReturn(headersSpecMock);
		when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);

		Vehicle vehicleObj = new Vehicle(1073L, "SANTA FE 7PAS HGS AC 2.5 5P 4X2 TA", 2023, 55690.0, 0.0, 55690.0, 0,
				"36");

		when(responseSpecMock.bodyToFlux(Vehicle.class)).thenReturn(Flux.just(vehicleObj));

		// When
		Flux<Vehicle> vehicle = vehicleService.getVehicles(VehicleModelEnum.SANTA_FE);

		// Then
		assertNotNull(vehicle);
	}

	@Test
	public void getVehicle_GRAND_shouldReturnVehicle() {
		// Given
		final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
		final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
		final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

		when(webClientVehicle.get()).thenReturn(uriSpecMock);
		when(uriSpecMock.uri("/api/versiones/1/1038")).thenReturn(headersSpecMock);
		when(headersSpecMock.accept(MediaType.APPLICATION_JSON)).thenReturn(headersSpecMock);
		when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);

		Vehicle vehicleObj = new Vehicle(100035L, "GRAND I10 5P STD", 2023, 14590.0, 0.0, 14590.0, 0, "42");

		when(responseSpecMock.bodyToFlux(Vehicle.class)).thenReturn(Flux.just(vehicleObj));

		// When
		Flux<Vehicle> vehicle = vehicleService.getVehicles(VehicleModelEnum.GRAND);

		// Then
		assertNotNull(vehicle);
	}

}
