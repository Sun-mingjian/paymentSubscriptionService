package com.subscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.subscription.model.Subscription;
import com.subscription.service.PaymentService;

@Controller
public class SubscriptionController {

	@Autowired
	PaymentService paymentService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String processPayment(ModelMap model, @RequestParam String amountValue,
			@RequestParam String subscriptionType, @RequestParam String startPaymentDate,
			@RequestParam String endPaymentDate, @RequestParam int dayOfWeek, @RequestParam int dayOfMonth) {

		System.out.println("asdfasdfa");

		Subscription subscription = paymentService.initiateSubscription(amountValue, subscriptionType, startPaymentDate,
				endPaymentDate, dayOfWeek, dayOfMonth);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(subscription);

		return jsonInString;
	}

}
