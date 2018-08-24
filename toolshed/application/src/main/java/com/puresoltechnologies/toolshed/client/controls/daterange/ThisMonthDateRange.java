package com.puresoltechnologies.toolshed.client.controls.daterange;

import java.time.LocalDate;

public class ThisMonthDateRange implements DefaultDateRange {

    private final LocalDate now = LocalDate.now();

    @Override
    public String getTitle() {
	return "This Month";
    }

    @Override
    public LocalDate getStartDate() {
	return LocalDate.of(now.getYear(), now.getMonth(), 1);
    }

    @Override
    public LocalDate getEndDate() {
	return now;
    }

}
