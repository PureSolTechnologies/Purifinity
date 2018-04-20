package com.puresoltechnologies.toolshed.client.controls.daterange;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.puresoltechnologies.toolshed.client.controls.daterange.DateRangePicker;
import com.puresoltechnologies.toolshed.client.controls.daterange.LastMonthDateRange;
import com.puresoltechnologies.toolshed.client.controls.daterange.LastSevenDaysDateRange;
import com.puresoltechnologies.toolshed.client.controls.daterange.LastThirdyDaysDateRange;
import com.puresoltechnologies.toolshed.client.controls.daterange.LastYearDateRange;
import com.puresoltechnologies.toolshed.client.controls.daterange.ThisMonthDateRange;
import com.puresoltechnologies.toolshed.client.controls.daterange.ThisYearDateRange;
import com.puresoltechnologies.toolshed.client.controls.daterange.TodayDateRange;
import com.puresoltechnologies.toolshed.client.controls.daterange.YesterdayDateRange;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DateRangePickerIT extends ApplicationTest {

    private DateRangePicker picker = null;

    @Override
    public void start(Stage stage) {
	stage.initStyle(StageStyle.DECORATED);
	picker = new DateRangePicker();
	picker.addDefaultDateRange(new TodayDateRange());
	picker.addDefaultDateRange(new YesterdayDateRange());
	picker.addDefaultDateRange(new LastSevenDaysDateRange());
	picker.addDefaultDateRange(new LastThirdyDaysDateRange());
	picker.addDefaultDateRange(new LastMonthDateRange());
	picker.addDefaultDateRange(new LastYearDateRange());
	picker.addDefaultDateRange(new ThisMonthDateRange());
	picker.addDefaultDateRange(new ThisYearDateRange());
	Scene scene = new Scene(picker);
	stage.setScene(scene);
	stage.show();
    }

    @Test
    public void testDialog() throws InterruptedException {
	Thread.sleep(250);
	System.out.println(picker.getStartDate());
	System.out.println(picker.getEndDate());
    }
}
