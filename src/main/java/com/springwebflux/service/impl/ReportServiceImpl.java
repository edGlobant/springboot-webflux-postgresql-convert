package com.springwebflux.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.springwebflux.service.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springwebflux.model.request.ReportRequest;
import com.springwebflux.model.response.ReportResponse;
import com.springwebflux.model.response.ReportResponseData;
import com.springwebflux.repository.BuyTableRepository;

import reactor.core.publisher.Mono;

@Service
public class ReportServiceImpl implements ReportService {

	private BuyTableRepository buyTableRepository;

	public ReportServiceImpl(BuyTableRepository buyTableRepository) {
		this.buyTableRepository = buyTableRepository;
	}

	@Transactional
	@Override
	public Mono<ReportResponse> report(ReportRequest reportRequest) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateLocal = LocalDate.parse(reportRequest.getData().getDate(), formatter);
		Date convertedDate = Date.from(dateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

		Mono<Double> amountUSD = buyTableRepository.getTotalAmountSoldUsd(convertedDate,
				reportRequest.getData().getModel(), reportRequest.getData().getCryptocurrency())
				.switchIfEmpty(Mono.error(new RuntimeException("Coudlnt find total USD")));

		Mono<Double> amountCrypto = buyTableRepository.getTotalAmountSoldCrypto(convertedDate,
				reportRequest.getData().getModel(), reportRequest.getData().getCryptocurrency())
				.switchIfEmpty(Mono.error(new RuntimeException("Coudlnt find total CRYPTO")));

		ReportResponse reportResponse = new ReportResponse();

		return amountUSD.flatMap(amountU -> {
			return amountCrypto.flatMap(amountC -> {
				ReportResponseData reportData = new ReportResponseData();
				reportData.setDate(reportRequest.getData().getDate());
				reportData.setModel(reportRequest.getData().getModel());
				reportData.setCryptocurrency(reportRequest.getData().getCryptocurrency());
				reportData.setUsdAmount(amountU);
				reportData.setCryptocurrencyAmount(amountC);

				reportResponse.setData(reportData);

				return Mono.just(reportResponse);
			}).switchIfEmpty(Mono.just(reportResponse));
		});
	}
}