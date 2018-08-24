package com.puresoltechnologies.toolshed.jpcap;

import org.junit.jupiter.api.Test;

public class JPCapTest {

    @Test
    public void testPcapLookupDev() throws JPCapException {
	System.err.println(JPCap.pcapLookupDev());
    }

    @Test
    public void testOpenClose() throws JPCapException {
	String device = JPCap.pcapLookupDev();
	long id = JPCap.pcapOpenLive(device, 1024, 1, 1000);
	System.out.println(id);
	JPCap.pcapClose(id);
    }

}
