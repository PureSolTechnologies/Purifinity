package com.puresoltechnologies.toolshed.client.controls.daterange;

import java.time.LocalDate;

public class LastSevenDaysDateRange implements DefaultDateRange {

    @Override
    public String getTitle() {
	return "Last 7 Days";
    }

    @Override
    public LocalDate getStartDate() {
	return LocalDate.now().minusDays(7);
    }

    @Override
    public LocalDate getEndDate() {
	return LocalDate.now();
    }

}
