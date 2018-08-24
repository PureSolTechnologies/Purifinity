package com.puresoltechnologies.toolshed.client.controls.daterange;

import java.time.LocalDate;

public class LastYearDateRange implements DefaultDateRange {

    private final int lastYear = LocalDate.now().getYear() - 1;

    @Override
    public String getTitle() {
	return "Last Year";
    }

    @Override
    public LocalDate getStartDate() {
	return LocalDate.of(lastYear, 1, 1);
    }

    @Override
    public LocalDate getEndDate() {
	return LocalDate.of(lastYear, 12, 31);
    }

}
