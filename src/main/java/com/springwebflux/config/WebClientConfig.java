package com.springwebflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClientCrypto() {
		return WebClient.builder().baseUrl("https://http-api.livecoinwatch.com/").build();
	}

	@Bean
	public WebClient webClientCryptoAlt() {
		return WebClient.builder().baseUrl("https://api.coinlore.net/").build();
	}

	@Bean
	public WebClient webClientVehicles() {
		return WebClient.builder().baseUrl("https://kerner.hyundai.com.ec").build();
	}
}
