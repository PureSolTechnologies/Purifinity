package com.puresol.gui.osgi;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.puresol.osgi.AbstractOSGIServiceManager;

public class BundleConfiguratorManager extends
		AbstractOSGIServiceManager<BundleConfigurator> {

	private static final Logger logger = Logger
			.getLogger(BundleConfiguratorManager.class);

	private final List<BundleConfigurator> languages = new ArrayList<BundleConfigurator>();

	@Override
	public final List<BundleConfigurator> getAll() {
		return languages;
	}

	@Override
	public final void register(BundleConfigurator language) {
		logger.info("Register programminglanguage '"
				+ language.getClass().getName() + "'...");
		languages.add(language);
	}

	@Override
	public final void unregister(BundleConfigurator language) {
		logger.info("Unregister programminglanguage '"
				+ language.getClass().getName() + "'...");
		languages.remove(language);
	}

}
