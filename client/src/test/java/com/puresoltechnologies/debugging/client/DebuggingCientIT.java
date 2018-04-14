package com.puresoltechnologies.debugging.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import com.puresoltechnologies.debugging.client.DebuggingClient;

public class DebuggingCientIT extends FxRobot {

    @Before
    public void before() throws Exception {
	FxToolkit.registerPrimaryStage();
	FxToolkit.setupApplication(DebuggingClient.class);
    }

    @After
    public void after() throws Exception {
	FxToolkit.registerPrimaryStage();
	FxToolkit.setupApplication(DebuggingClient.class);
    }

    @Test
    public void test() throws InterruptedException {
	Thread.sleep(100);
	clickOn("File");
	Thread.sleep(100);
	clickOn("Quit...");
    }

}
