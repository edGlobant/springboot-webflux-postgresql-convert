package com.springwebflux.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springwebflux.entity.BuyTable;
import com.springwebflux.entity.ConvertTable;
import com.springwebflux.model.request.BuyRequest;
import com.springwebflux.model.response.BuyResponse;
import com.springwebflux.model.response.BuyResponseData;
import com.springwebflux.repository.BuyTableRepository;
import com.springwebflux.repository.ConvertTableRepository;

import reactor.core.publisher.Mono;

@Service
public class BuyService {

	private BuyTableRepository buyTableRepository;

	private ConvertTableRepository convertTableRepository;

	public BuyService(BuyTableRepository buyTableRepository, ConvertTableRepository convertTableRepository) {
		this.buyTableRepository = buyTableRepository;
		this.convertTableRepository = convertTableRepository;
	}

	@Transactional
	public Mono<BuyResponse> buy(BuyRequest buyRequest) {
		
		// TODO: If the id is in cache use that convertion info without going to DB.
		
		Mono<ConvertTable> convert = convertTableRepository.findByConvertionIdAndVersion(
				buyRequest.getData().getConvertionId(), buyRequest.getData().getVersion());

		// TODO: Maybe error handling? or creation of a new convertion when id incorrect?
		
		LocalDate currentDate = LocalDate.now();
		Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		return convert.flatMap(c -> {
			BuyResponseData buyData = new BuyResponseData();
			buyData.setFullName(buyRequest.getData().getFullName());
			buyData.setVersion(buyRequest.getData().getVersion());
			buyData.setModel(c.getModel());
			buyData.setCryptocurrency(c.getCryptocurrency());
			buyData.setPriceUsd(c.getPriceUsd());
			buyData.setPriceCryptocurrency(c.getPriceCryptocurrency());
			buyData.setDate(date);
			buyData.setPurchaseId(UUID.randomUUID().toString());

			BuyResponse buyResponse = new BuyResponse();
			buyResponse.setData(buyData);

			BuyTable entity = new BuyTable();
			entity.setFullName(buyRequest.getData().getFullName());
			entity.setVersion(buyRequest.getData().getVersion());
			entity.setModel(c.getModel());
			entity.setCryptocurrency(c.getCryptocurrency());
			entity.setPriceUsd(c.getPriceUsd());
			entity.setPriceCryptocurrency(c.getPriceCryptocurrency());
			entity.setDate(date);
			entity.setPurchaseId(buyData.getPurchaseId());

			buyTableRepository.save(entity).subscribe();

			return Mono.just(buyResponse);
		});
	}
}
