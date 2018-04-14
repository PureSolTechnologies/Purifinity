package com.puresoltechnologies.debugging.client.controls.daterange;

import java.time.LocalDate;

public class YesterdayDateRange implements DefaultDateRange {

    @Override
    public String getTitle() {
	return "Yesterday";
    }

    @Override
    public LocalDate getStartDate() {
	return LocalDate.now().minusDays(1);
    }

    @Override
    public LocalDate getEndDate() {
	return LocalDate.now().minusDays(1);
    }

}
