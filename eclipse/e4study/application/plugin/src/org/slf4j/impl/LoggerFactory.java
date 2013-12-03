package org.slf4j.impl;

import org.eclipse.equinox.log.ExtendedLogService;
import org.osgi.framework.ServiceReference;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.puresoltechnologies.coding.richclient.application.Activator;

public class LoggerFactory implements ILoggerFactory {

	@Override
	public Logger getLogger(String loggerName) {
		ServiceReference<ExtendedLogService> serviceReference = Activator
				.getBundleContext().getServiceReference(
						ExtendedLogService.class);
		ExtendedLogService service = Activator.getBundleContext().getService(
				serviceReference);
		return new LoggerImpl(service.getLogger(loggerName));
	}

}
