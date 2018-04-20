package com.puresoltechnologies.toolshed.client.controls.daterange;

import java.time.LocalDate;

public class LastThirdyDaysDateRange implements DefaultDateRange {

    @Override
    public String getTitle() {
	return "Last 30 Days";
    }

    @Override
    public LocalDate getStartDate() {
	return LocalDate.now().minusDays(30);
    }

    @Override
    public LocalDate getEndDate() {
	return LocalDate.now();
    }

}
