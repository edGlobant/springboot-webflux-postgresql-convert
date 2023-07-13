package com.springwebflux.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table("buy_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuyTable {

	@Id
	private Long id;

	@Column("fullName")
	private String fullName;

	@Column
	private String version;

	@Column
	private String model;

	@Column
	private String cryptocurrency;

	@Column("priceUsd")
	private Double priceUsd;

	@Column("priceCryptocurrency")
	private Double priceCryptocurrency;

	@Column
	private Date date;

	@Column("purchaseId")
	private String purchaseId;

}
