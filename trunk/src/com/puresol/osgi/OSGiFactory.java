package com.puresol.osgi;

public class OSGiFactory {

    private static OSGi osgi = null;

    public static OSGi create() {
	if (osgi == null) {
	    createInstance();
	}
	return osgi;
    }

    private static synchronized void createInstance() {
	if (osgi == null) {
	    osgi = new FelixOSGi();
	}
    }
}
