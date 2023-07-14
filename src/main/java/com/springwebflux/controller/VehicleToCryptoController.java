package com.springwebflux.controller;

import com.springwebflux.service.BuyService;
import com.springwebflux.service.ConvertService;
import com.springwebflux.service.ReportService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springwebflux.model.request.BuyRequest;
import com.springwebflux.model.request.ConvertRequest;
import com.springwebflux.model.request.ReportRequest;
import com.springwebflux.model.response.BuyResponse;
import com.springwebflux.model.response.ConvertResponse;
import com.springwebflux.model.response.ReportResponse;
import com.springwebflux.service.impl.ReportServiceImpl;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleToCryptoController {

	private ConvertService convertService;

	private BuyService buyService;

	private ReportService reportService;

	public VehicleToCryptoController(ConvertService convertService, BuyService buyService,
									 ReportService reportService) {
		this.convertService = convertService;
		this.buyService = buyService;
		this.reportService = reportService;
	}

	@PostMapping("/convert")
	public Mono<ConvertResponse> convert(@RequestBody ConvertRequest convertRequest) {
		return convertService.convert(convertRequest);
	}

	@PostMapping("/buy")
	public Mono<BuyResponse> buy(@RequestBody BuyRequest buyRequest) {
		return buyService.buy(buyRequest);
	}

	@GetMapping("/report")
	public Mono<ReportResponse> report(@RequestBody ReportRequest reportRequest) {
		return reportService.report(reportRequest);
	}

	@ExceptionHandler(Exception.class)
	public Mono<String> handleException(Exception ex) {
		return Mono.just("An error occurred: " + ex.getMessage());
	}
}
