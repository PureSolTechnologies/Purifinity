package com.puresoltechnologies.debugging.client.controls.interval;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.puresoltechnologies.debugging.client.controls.interval.PeriodPicker;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PeriodPickerIT extends ApplicationTest {

    private PeriodPicker picker = null;

    @Override
    public void start(Stage stage) {
	stage.initStyle(StageStyle.DECORATED);
	picker = new PeriodPicker();
	Scene scene = new Scene(picker);
	stage.setScene(scene);
	stage.show();
    }

    @Test
    public void testDialog() throws InterruptedException {
	Thread.sleep(250);
	System.out.println(picker.getAmount());
	System.out.println(picker.getUnit());
    }
}
