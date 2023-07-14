package com.springwebflux.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import com.springwebflux.service.BuyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springwebflux.entity.BuyTable;
import com.springwebflux.entity.ConvertTable;
import com.springwebflux.model.request.BuyRequest;
import com.springwebflux.model.response.BuyResponse;
import com.springwebflux.model.response.BuyResponseData;
import com.springwebflux.repository.BuyTableRepository;
import com.springwebflux.repository.ConvertTableRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BuyServiceImpl implements BuyService {

    private BuyTableRepository buyTableRepository;

    private ConvertTableRepository convertTableRepository;

    public BuyServiceImpl(BuyTableRepository buyTableRepository, ConvertTableRepository convertTableRepository) {
        this.buyTableRepository = buyTableRepository;
        this.convertTableRepository = convertTableRepository;
    }

    @Transactional
    @Override
    public Mono<BuyResponse> buy(BuyRequest buyRequest) {

        // TODO: If the id is in cache use that convertion info without going to DB.

        Flux<ConvertTable> convert = convertTableRepository.findByConvertionId(buyRequest.getData().getConvertionId()).switchIfEmpty(Flux.error(new RuntimeException("Coudlnt find convertionid")));

        LocalDate currentDate = LocalDate.now();
        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return convert.filter(c -> {
            return c.getVersion().equals(buyRequest.getData().getVersion());
        }).flatMap(c -> {
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
        }).single();
    }
}
