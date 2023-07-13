package com.springwebflux.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springwebflux.entity.ConvertTable;
import com.springwebflux.model.constants.CryptoModelEnum;
import com.springwebflux.model.constants.VehicleModelEnum;
import com.springwebflux.model.json.VehicleVersion;
import com.springwebflux.model.request.ConvertRequest;
import com.springwebflux.model.response.ConvertResponse;
import com.springwebflux.model.response.ConvertResponseData;
import com.springwebflux.repository.ConvertTableRepository;

import reactor.core.publisher.Mono;

@Service
public class ConvertService {

	private VehicleService vehicleService;
	private CryptoService cryptoService;
	private ConvertTableRepository convertTableRepository;

	public ConvertService(VehicleService vehicleService, CryptoService cryptoService,
			ConvertTableRepository convertTableRepository) {
		this.vehicleService = vehicleService;
		this.cryptoService = cryptoService;
		this.convertTableRepository = convertTableRepository;
	}

	@Transactional
	public Mono<ConvertResponse> convert(ConvertRequest convertRequest) {

		VehicleModelEnum vehicleModel = VehicleModelEnum.valueOf(convertRequest.getData().getModel());
		CryptoModelEnum vehicleCrypto = CryptoModelEnum.valueOf(convertRequest.getData().getCryptocurrency());

		// TODO: Si la cripto no es ETH o BTC throw error
		
		return vehicleService.getVehicles(vehicleModel).flatMap(v -> {
			return cryptoService.getCryptoPrize(vehicleCrypto).flatMap(c -> {
				VehicleVersion vehicleVersion = new VehicleVersion();
				vehicleVersion.setModel(vehicleModel.getModel());
				vehicleVersion.setVersion(v.getVerNombre());
				vehicleVersion.setPriceUsd(v.getVeaPrecioFinal());
				vehicleVersion.setPriceCryptocurrency(v.getVeaPrecioFinal() / c);
				vehicleVersion.setCryptocurrency(vehicleCrypto.name());
				return Mono.just(vehicleVersion);
			});
		}).collectList().flatMap(vl -> {
			ConvertResponseData convertData = new ConvertResponseData();
			convertData.setConvertionId(UUID.randomUUID().toString());
			convertData.setConversionTimelife("20 seconds");
			convertData.setVersions(vl);

			ConvertResponse convertResponse = new ConvertResponse();
			convertResponse.setData(convertData);

			List<ConvertTable> entityList = vl.stream().map(v -> {
				ConvertTable entity = new ConvertTable();
				entity.setConversionTimelife(convertData.getConversionTimelife());
				entity.setConvertionId(convertData.getConvertionId());
				entity.setModel(v.getModel());
				entity.setVersion(v.getVersion());
				entity.setPriceUsd(v.getPriceUsd());
				entity.setPriceCryptocurrency(v.getPriceCryptocurrency());
				entity.setCryptocurrency(v.getCryptocurrency());
				return entity;
			}).toList();

			convertTableRepository.saveAll(entityList).subscribe();

			// TODO: save to cache

			return Mono.just(convertResponse);
		});
	}
}
