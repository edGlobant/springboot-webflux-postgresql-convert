package com.springwebflux.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuyResponseData {

	@JsonProperty("fullName")
	private String fullName;
	
	@JsonProperty("version")
	private String version;

	@JsonProperty("model")
	private String model;

	@JsonProperty("cryptocurrency")
	private String cryptocurrency;
	
	@JsonProperty("priceUsd")
	private Double priceUsd;

	@JsonProperty("priceCryptocurrency")
	private Double priceCryptocurrency;

	@JsonProperty("date")
	private Date date;

	@JsonProperty("purchaseId")
	private String purchaseId;
	
}