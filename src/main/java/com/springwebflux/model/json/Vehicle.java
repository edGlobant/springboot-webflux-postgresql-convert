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
public class Vehicle {
	@JsonProperty("VER_CODIGO")
	private Long verCodigo;

	@JsonProperty("VER_NOMBRE")
	private String verNombre;

	@JsonProperty("VEA_ANIO")
	private Integer veaAnio;

	@JsonProperty("VEA_PRECIO_PVP")
	private Double veaPrecioPvp;

	@JsonProperty("VEA_BONO")
	private Double veaBono;

	@JsonProperty("VEA_PRECIO_FINAL")
	private Double veaPrecioFinal;

	@JsonProperty("VEA_DISCAPACIDAD_100")
	private Integer veaDiscapacidad100;

	@JsonProperty("VER_COD_SGC")
	private String verCodSgc;
}
