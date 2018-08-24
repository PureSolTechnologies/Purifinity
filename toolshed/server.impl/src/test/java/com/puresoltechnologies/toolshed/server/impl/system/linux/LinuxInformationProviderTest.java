package com.puresoltechnologies.toolshed.server.impl.system.linux;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.server.api.system.SystemLoad;

public class LinuxInformationProviderTest {

    private static LinuxInformationProvider provider;

    @BeforeAll
    public static void initialize() {
	provider = new LinuxInformationProvider();
    }

    @Test
    public void test() throws InterruptedException {
	int i = 0;
	while (i < 5) {
	    SystemLoad load = provider.getLoad();
	    Thread.sleep(1000);
	    i++;
	}
    }

}
