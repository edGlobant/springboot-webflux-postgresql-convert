package com.springwebflux.controller.unit;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.springwebflux.controller.VehicleToCryptoController;
import com.springwebflux.model.response.BuyResponse;
import com.springwebflux.model.response.ConvertResponse;
import com.springwebflux.model.response.ReportResponse;
import com.springwebflux.service.impl.BuyServiceImpl;
import com.springwebflux.service.impl.ConvertServiceImpl;
import com.springwebflux.service.impl.ReportServiceImpl;

import reactor.core.publisher.Mono;

@WebFluxTest(controllers = VehicleToCryptoController.class)
@AutoConfigureWebTestClient
public class VehicleToCryptoControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ConvertServiceImpl convertServiceImpl;

	@MockBean
	private BuyServiceImpl buyServiceImpl;

	@MockBean
	private ReportServiceImpl reportServiceImpl;

	static String CONVERT_URL = "/v1/convert";

	static String BUY_URL = "/v1/buy";

	static String REPORT_URL = "/v1/report";

	@Test
	void convert_shouldGetSuccessConvertResponse() {
		// Given
		when(convertServiceImpl.convert(Mockito.any())).thenReturn(Mono.just(new ConvertResponse()));

		// When
		webTestClient.post().uri(CONVERT_URL).exchange().expectStatus().is2xxSuccessful();
		
		// Then
	}

	@Test
	void convert_shouldGetSuccessBuyResponse() {
		// Given
		when(buyServiceImpl.buy(Mockito.any())).thenReturn(Mono.just(new BuyResponse()));

		// When
		webTestClient.post().uri(BUY_URL).exchange().expectStatus().is2xxSuccessful();
		
		// Then
	}

	@Test
	void convert_shouldGetSuccessReportResponse() {
		// Given
		when(reportServiceImpl.report(Mockito.any())).thenReturn(Mono.just(new ReportResponse()));

		// When
		webTestClient.get().uri(REPORT_URL).exchange().expectStatus().is2xxSuccessful();
		
		// Then
	}
}
