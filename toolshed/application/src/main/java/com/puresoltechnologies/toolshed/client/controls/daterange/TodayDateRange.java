package com.puresoltechnologies.toolshed.client.controls.daterange;

import java.time.LocalDate;

public class TodayDateRange implements DefaultDateRange {

    @Override
    public String getTitle() {
	return "Today";
    }

    @Override
    public LocalDate getStartDate() {
	return LocalDate.now();
    }

    @Override
    public LocalDate getEndDate() {
	return LocalDate.now();
    }

}
