package com.puresoltechnologies.toolshed.jpcap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PackageCapture {

    private static final Logger logger = LoggerFactory.getLogger(PackageCapture.class);

    private static final Object instanceLock = new Object();
    private static PackageCapture instance = null;

    public static PackageCapture getInstance() {
	if (instance == null) {
	    synchronized (instanceLock) {
		if (instance == null) {
		    instance = new PackageCapture();
		}
	    }
	}
	return instance;
    }

    private PackageCapture() {

    }

}
