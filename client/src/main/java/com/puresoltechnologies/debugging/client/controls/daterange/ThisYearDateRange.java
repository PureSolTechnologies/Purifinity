package com.puresoltechnologies.debugging.client.controls.daterange;

import java.time.LocalDate;

public class ThisYearDateRange implements DefaultDateRange {

    private final LocalDate now = LocalDate.now();

    @Override
    public String getTitle() {
	return "This Year";
    }

    @Override
    public LocalDate getStartDate() {
	return LocalDate.of(now.getYear(), 1, 1);
    }

    @Override
    public LocalDate getEndDate() {
	return now;
    }

}
