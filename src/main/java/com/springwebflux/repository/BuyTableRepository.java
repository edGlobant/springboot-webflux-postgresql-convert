package com.springwebflux.repository;

import java.util.Date;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.springwebflux.entity.BuyTable;

import reactor.core.publisher.Mono;

@Repository
public interface BuyTableRepository extends ReactiveCrudRepository<BuyTable, Long> {

	@Query("SELECT SUM(priceUsd) FROM buy_table WHERE date = :date AND model = :model AND cryptocurrency = :cryptocurrency")
	Mono<Double> getTotalAmountSoldUsd(Date date, String model, String cryptocurrency);

	@Query("SELECT SUM(priceCryptocurrency) FROM buy_table WHERE date = :date AND model = :model AND cryptocurrency = :cryptocurrency")
	Mono<Double> getTotalAmountSoldCrypto(Date date, String model, String cryptocurrency);
}
