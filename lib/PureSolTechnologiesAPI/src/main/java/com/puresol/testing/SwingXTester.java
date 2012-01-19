package com.puresol.testing;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.gui.Application;

public class SwingXTester {

    private static final Logger logger = LoggerFactory
	    .getLogger(SwingXTester.class);

    private Application application = null;

    public SwingXTester() {
    }

    public SwingXTester(Application application) {
	this.application = application;
    }

    public void run() {
	try {
	    if (application != null) {
		SwingUtilities.invokeAndWait(application);
	    }
	} catch (InterruptedException e) {
	    logger.error(e.getMessage(), e);
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage(), e);
	}
    }
}
