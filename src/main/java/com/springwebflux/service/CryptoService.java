package com.springwebflux.service;

import com.jayway.jsonpath.JsonPath;
import com.springwebflux.model.constants.CryptoModelEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CryptoService {

	public final WebClient webClientCrypto;

	public final WebClient webClientCryptoAlt;

	public Mono<Double> getCryptoPrize(CryptoModelEnum modelo) {
		String URI = findUriByCrypto(modelo);

		Mono<String> monoString = webClientCrypto.get().uri(URI).retrieve().bodyToMono(String.class);

		Mono<String> dataMono = monoString.flatMap(json -> {
			Double data = JsonPath.parse(json).read("$.data.lastPrice", Double.class);
			return Mono.just(data);
		}).map(String::valueOf);

		return dataMono.map(Double::parseDouble);
	}
	public Mono<Double> getCryptoPrize2(CryptoModelEnum modelo) {
		String URI = findUriByCrypto(modelo);

		Mono<String> monoString = webClientCryptoAlt.get().uri(URI).retrieve().bodyToMono(String.class);

		Mono<String> dataMono = monoString.flatMap(json -> {
			Double data = JsonPath.parse(json).read("$.price_usd", Double.class);
			return Mono.just(data);
		}).map(String::valueOf);

		return dataMono.map(Double::parseDouble);
	}

	// Crypto URLs
	static String BTC_BASE_URL = "/coins/BTC/about?currency=USD";
	static String ETH_BASE_URL = "/coins/ETH/about?currency=USD";

	private String findUriByCrypto(CryptoModelEnum modelo) {
		switch (modelo) {
		case BTC:
			return BTC_BASE_URL;
		case ETH:
			return ETH_BASE_URL;
		}
		return null;
	}
}
