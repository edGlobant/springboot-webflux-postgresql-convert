package com.springwebflux.controller.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.springwebflux.model.constants.CryptoModelEnum;
import com.springwebflux.model.constants.VehicleModelEnum;
import com.springwebflux.model.request.BuyRequest;
import com.springwebflux.model.request.BuyRequestData;
import com.springwebflux.model.request.ConvertRequest;
import com.springwebflux.model.request.ConvertRequestData;
import com.springwebflux.model.request.ReportRequest;
import com.springwebflux.model.request.ReportRequestData;
import com.springwebflux.model.response.BuyResponse;
import com.springwebflux.model.response.ConvertResponse;
import com.springwebflux.model.response.ReportResponse;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class VehicleToCryptoControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	static String CONVERT_URL = "/v1/convert";

	static String BUY_URL = "/v1/buy";

	static String REPORT_URL = "/v1/report";

	@BeforeEach
	public void init() {

	}

	@Test
	void convert_shouldReturnMonoWithConvertResponse() {
		// Given
		ConvertRequest convertRequest = new ConvertRequest();
		ConvertRequestData convertRequestData = new ConvertRequestData(VehicleModelEnum.ACCENT.name(),
				CryptoModelEnum.BTC.name());
		convertRequest.setData(convertRequestData);

		// When
		Mono<ConvertResponse> convertResponse = webTestClient.post().uri(CONVERT_URL)
				.contentType(MediaType.APPLICATION_JSON).bodyValue(convertRequest).exchange().expectStatus().isOk()
				.returnResult(ConvertResponse.class).getResponseBody().single();

		// Then
		assertNotNull(convertResponse);
	}

	@Test
	void buy_shouldReturnMonoWithBuyResponse() {
		// Given
		BuyRequestData buyRequestData = new BuyRequestData();
		buyRequestData.setConvertionId(UUID.randomUUID().toString());
		buyRequestData.setFullName("Juan Perez");
		buyRequestData.setModel("GRAND");
		buyRequestData.setVersion("GRAND I10 5P GLS");
		buyRequestData.setCryptocurrency(CryptoModelEnum.BTC.name());
		BuyRequest buyRequest = new BuyRequest(buyRequestData);

		// When
		Mono<BuyResponse> buyResponse = webTestClient.post().uri(CONVERT_URL).contentType(MediaType.APPLICATION_JSON)
				.bodyValue(buyRequest).exchange().expectStatus().isOk().returnResult(BuyResponse.class)
				.getResponseBody().single();

		// Then
		assertNotNull(buyResponse);
	}

	@Test
	void report_shouldReturnMonoWithReportResponse() {
		// Given
		ReportRequestData reportRequestData = new ReportRequestData();
		reportRequestData.setDate(null);
		reportRequestData.setModel("GRAND");
		reportRequestData.setCryptocurrency(CryptoModelEnum.BTC.name());
		ReportRequest reportRequest = new ReportRequest();

		// When
		Mono<ReportResponse> reportResponse = webTestClient.post().uri(CONVERT_URL)
				.contentType(MediaType.APPLICATION_JSON).bodyValue(reportRequest).exchange().expectStatus().isOk()
				.returnResult(ReportResponse.class).getResponseBody().single();

		// Then
		assertNotNull(reportResponse);
	}
}
