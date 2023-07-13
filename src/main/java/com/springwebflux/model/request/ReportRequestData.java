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
public class ReportRequestData {

	@JsonProperty("date")
	private String date;

	@JsonProperty("model")
	private String model;

	@JsonProperty("cryptocurrency")
	private String cryptocurrency;
}