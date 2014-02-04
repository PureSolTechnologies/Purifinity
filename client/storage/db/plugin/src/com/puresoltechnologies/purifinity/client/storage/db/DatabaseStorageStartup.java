package com.puresoltechnologies.purifinity.client.storage.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.client.common.osgi.AbstractStartup;
import com.puresoltechnologies.purifinity.framework.store.db.Activator;

public class DatabaseStorageStartup extends AbstractStartup {

	/**
	 * Is the logger used to log.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(DatabaseStorageStartup.class);

	public DatabaseStorageStartup() {
		super(Activator.class);
		logger.debug("Trigger activation of '"
				+ com.datastax.driver.Activator.class.getPackage().getName()
				+ "'...");
		logger.debug("Trigger activation of '"
				+ com.thinkaurelius.titan.Activator.class.getPackage()
						.getName() + "'...");
	}

}