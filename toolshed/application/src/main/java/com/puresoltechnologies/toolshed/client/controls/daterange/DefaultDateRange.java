package com.puresoltechnologies.toolshed.client.controls.daterange;

import java.time.LocalDate;

/**
 * Provides a default period generated for {@link DateRangePicker}.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface DefaultDateRange {

    /**
     * This method returns the title to be presented in a selection.
     * 
     * @return A String is returned containing the title.
     */
    public String getTitle();

    public LocalDate getStartDate();

    public LocalDate getEndDate();

}
