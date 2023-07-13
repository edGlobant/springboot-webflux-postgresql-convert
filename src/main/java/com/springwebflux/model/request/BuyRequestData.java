package com.springwebflux.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuyRequestData {

	@JsonProperty("convertionId")
	private String convertionId;

	@JsonProperty("fullName")
	private String fullName;

	@JsonProperty("version")
	private String version;

	@JsonProperty("model")
	private String model;

	@JsonProperty("cryptocurrency")
	private String cryptocurrency;
}