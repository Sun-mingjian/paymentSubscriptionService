package com.subscription.model;

public class Amount {
	private double value;
	private String currency;

	public Amount(double value, String currency) {
		super();
		this.value = value;
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
