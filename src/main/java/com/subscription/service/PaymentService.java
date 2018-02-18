package com.subscription.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subscription.model.Amount;
import com.subscription.model.Subscription;

@Service
public class PaymentService {

	@Autowired
	private InvoiceDateService invoiceDateServce;

	public Subscription initiateSubscription(String amountValue, String subscriptionType, String startPaymentDate,
			String endPaymentDate, int dayOfWeek, int dayOfMonth) {

		String uuid = UUID.randomUUID().toString();

		Amount amount = new Amount(Double.parseDouble(amountValue), "AUD");

		List<String> invoiceDate = new ArrayList<String>();

		invoiceDate = invoiceDateServce.getAllInvoiceDate(startPaymentDate, endPaymentDate, subscriptionType, dayOfWeek,
				dayOfMonth);

		Subscription subsription = new Subscription(uuid, amount, subscriptionType, invoiceDate);

		return subsription;

	}

}
