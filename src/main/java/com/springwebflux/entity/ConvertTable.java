package com.springwebflux.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table("convert_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConvertTable {

	@Column("convertionId")
	private String convertionId;

	@Column("conversionTimelife")
	private String conversionTimelife;

	@Column
	private String model;

	@Column
	private String version;

	@Column("priceUsd")
	private Double priceUsd;

	@Column("priceCryptocurrency")
	private Double priceCryptocurrency;

	@Column
	private String cryptocurrency;

}
