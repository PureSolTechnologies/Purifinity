package com.puresoltechnologies.debugging.client.controls.daterange;

import java.time.LocalDate;
import java.time.Month;

public class LastMonthDateRange implements DefaultDateRange {

    private final LocalDate now = LocalDate.now();
    private final int thisYear = now.getYear();
    private final Month thisMonth = now.getMonth();

    @Override
    public String getTitle() {
	return "Last Month";
    }

    @Override
    public LocalDate getStartDate() {
	return LocalDate.of(thisYear, thisMonth, 1).minusMonths(1);
    }

    @Override
    public LocalDate getEndDate() {
	LocalDate lastMonthStart = LocalDate.of(thisYear, thisMonth, 1).minusMonths(1);
	return LocalDate.of(lastMonthStart.getYear(), lastMonthStart.getMonth(),
		lastMonthStart.getMonth().length(lastMonthStart.isLeapYear()));
    }

}
