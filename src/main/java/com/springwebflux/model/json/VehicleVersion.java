package com.springwebflux.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleVersion {
	@JsonProperty("model")
	private String model;

	@JsonProperty("version")
	private String version;

	@JsonProperty("priceUsd")
	private Double priceUsd;

	@JsonProperty("priceCryptocurrency")
	private Double priceCryptocurrency;

	@JsonProperty("cryptocurrency")
	private String cryptocurrency;

}
