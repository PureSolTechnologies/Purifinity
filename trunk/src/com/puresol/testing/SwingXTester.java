package com.puresol.testing;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;
import javax.swingx.Application;

import org.apache.log4j.Logger;

public class SwingXTester {

	private static final Logger logger = Logger.getLogger(SwingXTester.class);

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
