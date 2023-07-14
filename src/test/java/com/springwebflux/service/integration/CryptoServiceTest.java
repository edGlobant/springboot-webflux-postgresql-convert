package com.springwebflux.service.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.springwebflux.service.CryptoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import com.springwebflux.model.constants.CryptoModelEnum;

import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
public class CryptoServiceTest {

	@Autowired
	private CryptoService cryptoService;

	@Test
	public void getCryptoPrize_BTC_shouldReturnCorrectPrice() {
		// Given

		// When
		Mono<Double> prize = cryptoService.getCryptoPrize(CryptoModelEnum.BTC);

		// Then
		assertNotNull(prize.block());
	}

	@Test
	public void getCryptoPrize_ETH_shouldReturnCorrectPrice() {
		// Given
		
		// When
		Mono<Double> prize = cryptoService.getCryptoPrize(CryptoModelEnum.ETH);

		// Then
		assertNotNull(prize.block());
	}
}