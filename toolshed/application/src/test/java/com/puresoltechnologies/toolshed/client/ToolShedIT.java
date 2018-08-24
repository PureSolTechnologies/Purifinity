package com.puresoltechnologies.toolshed.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import com.puresoltechnologies.toolshed.client.ToolShedApplication;

public class ToolShedIT extends FxRobot {

    @Before
    public void before() throws Exception {
	FxToolkit.registerPrimaryStage();
	FxToolkit.setupApplication(ToolShedApplication.class);
    }

    @After
    public void after() throws Exception {
	FxToolkit.registerPrimaryStage();
	FxToolkit.setupApplication(ToolShedApplication.class);
    }

    @Test
    public void test() throws InterruptedException {
	Thread.sleep(100);
	clickOn("File");
	Thread.sleep(100);
	clickOn("Quit...");
    }

}
