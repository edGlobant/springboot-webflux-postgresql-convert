package com.springwebflux.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springwebflux.model.json.VehicleVersion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConvertResponseData {

	@JsonProperty("convertionId")
	private String convertionId;

	@JsonProperty("conversionTimelife")
	private String conversionTimelife;

	@JsonProperty("versions")
	private List<VehicleVersion> versions;
}