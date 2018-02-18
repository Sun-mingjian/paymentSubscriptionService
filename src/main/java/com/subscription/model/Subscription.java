package com.subscription.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

public class Subscription {

	private String id;
	private Amount amount;
	private String subscription_type;
	private List<String> invoice_dates;

	public Subscription(String id, Amount amount, String subscription_type, List<String> invoiceDate) {
		super();
		this.id = id;
		this.amount = amount;
		this.subscription_type = subscription_type;
		this.invoice_dates = invoiceDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public String getSubscription_type() {
		return subscription_type;
	}

	public void setSubscription_type(String subscription_type) {
		this.subscription_type = subscription_type;
	}

	public List<String> getInvoice_dates() {
		return invoice_dates;
	}

	public void setInvoice_dates(List<String> invoice_dates) {
		this.invoice_dates = invoice_dates;
	}

}
