package com.subscription.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class InvoiceDateService {

	public List<String> getAllInvoiceDate(String startPaymentDate, String endPaymentDate, String subscriptionType,
			int dayOfWeek, int dayOfMonth) {
		switch (subscriptionType) {
		case SubscriptionTypeConstant.DAILY:
			return calculateInvoiceDateForDailyPayment(startPaymentDate, endPaymentDate);

		case SubscriptionTypeConstant.WEEKLY:
			return calculateInvoiceDateForWeeklyPayment(startPaymentDate, endPaymentDate, dayOfWeek);

		case SubscriptionTypeConstant.MONTHLY:
			return calculateInvoiceDateFormMonthlyPayment(startPaymentDate, endPaymentDate, dayOfMonth);

		default:
			return null;
		}

	}

	private List<String> calculateInvoiceDateForDailyPayment(String startPaymentDate, String endPaymentDate) {
		List<String> dateList = new ArrayList<String>();
		LocalDate startDate = LocalDate.parse(startPaymentDate), endDate = LocalDate.parse(endPaymentDate);
		Stream.iterate(startDate, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(startDate, endDate) + 1)
				.forEach(thisDate -> dateList.add(thisDate.toString()));
		return dateList;

	}

	private List<String> calculateInvoiceDateForWeeklyPayment(String startPaymentDate, String endPaymentDate,
			int dayOfWeek) {
		List<String> dateList = new ArrayList<String>();
		LocalDate startDate = LocalDate.parse(startPaymentDate), endDate = LocalDate.parse(endPaymentDate);
		Stream.iterate(startDate, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(startDate, endDate) + 1)
				.forEach(thisDate -> {
					if (thisDate.getDayOfWeek().getValue() == dayOfWeek) {
						dateList.add(thisDate.toString());
					}
				});
		return dateList;
	}

	private List<String> calculateInvoiceDateFormMonthlyPayment(String startPaymentDate, String endPaymentDate,
			int dayOfMonth) {
		List<String> dateList = new ArrayList<String>();
		LocalDate startDate = LocalDate.parse(startPaymentDate), endDate = LocalDate.parse(endPaymentDate);
		Stream.iterate(startDate, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(startDate, endDate) + 1)
				.forEach(thisDate -> {
					System.out.println(thisDate.toString());
					if (thisDate.getDayOfMonth() == dayOfMonth) {
						dateList.add(thisDate.toString());
					}
					//if this dayOfMonth is beyond the last day of month, then we make it the last day of month as the invoice date 
					//instead of jumping this month
					//for example dayOfMonth is 31, but April or June does not have this day, so we make it 30th of April
					//and 30th of June as the invoice day.
					if (thisDate.getDayOfMonth() == thisDate.lengthOfMonth() && thisDate.lengthOfMonth() < dayOfMonth) {
						dateList.add(thisDate.withDayOfMonth(thisDate.lengthOfMonth()).toString());

					}
				});
		return dateList;
	}

}
