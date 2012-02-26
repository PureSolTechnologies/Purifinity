package com.puresol.gui.osgi;

import org.osgi.framework.Bundle;

public class OSGiUtils {

    public static String getTextForState(int state) {
	switch (state) {
	case Bundle.UNINSTALLED:
	    return "uninstalled";
	case Bundle.INSTALLED:
	    return "installed";
	case Bundle.RESOLVED:
	    return "resolved";
	case Bundle.STARTING:
	    return "starting";
	case Bundle.STOPPING:
	    return "stopping";
	case Bundle.ACTIVE:
	    return "active";
	}
	return "unknown";
    }
}
