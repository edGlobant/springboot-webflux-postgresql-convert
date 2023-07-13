package com.springwebflux.model.constants;

public enum VehicleModelEnum {
	ACCENT("ACCENT"), TUCSON("TUCSON"), SANTA_FE("SANTA_FE"), GRAND("GRAND");

	private String model;

	VehicleModelEnum(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}
}
