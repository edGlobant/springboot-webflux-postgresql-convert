package com.springwebflux.service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.springwebflux.service.CryptoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;

import com.springwebflux.model.constants.CryptoModelEnum;

import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
public class CryptoServiceTest {

	@MockBean(name = "webClientCrypto")
	private WebClient webClientCrypto;

	@MockBean(name = "webClientCryptoAlt")
	private WebClient webClientCryptoAlt;

	private CryptoService cryptoService;

	@BeforeEach
	public void init() {
		webClientCrypto = Mockito.mock(WebClient.class);
		cryptoService = new CryptoService(webClientCrypto,webClientCryptoAlt);
	}

	@Test
	public void getCryptoPrize_BTC_shouldReturnCorrectPrice() {
		// Given
		final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
		final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
		final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

		when(webClientCrypto.get()).thenReturn(uriSpecMock);
		when(uriSpecMock.uri("/coins/BTC/about?currency=USD")).thenReturn(headersSpecMock);
		when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
				.thenReturn(Mono.just("{ \"data\": { \"lastPrice\": 100000 } }"));

		// When
		Mono<Double> prize = cryptoService.getCryptoPrize(CryptoModelEnum.BTC);

		// Then
		assertEquals(100000.0, prize.block());
	}

	@Test
	public void getCryptoPrize_ETH_shouldReturnCorrectPrice() {
		// Given
		final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
		final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
		final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

		when(webClientCrypto.get()).thenReturn(uriSpecMock);
		when(uriSpecMock.uri("/coins/ETH/about?currency=USD")).thenReturn(headersSpecMock);
		when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
				.thenReturn(Mono.just("{ \"data\": { \"lastPrice\": 2000 } }"));

		// When
		Mono<Double> prize = cryptoService.getCryptoPrize(CryptoModelEnum.ETH);

		// Then
		assertEquals(2000.0, prize.block());
	}
}