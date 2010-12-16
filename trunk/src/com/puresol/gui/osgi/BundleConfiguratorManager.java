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

	private static BundleConfiguratorManager instance = null;

	public static BundleConfiguratorManager getInstance() {
		if (instance == null) {
			logger.info("No Evaluators instance initialized...");
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			logger.info("Create Evaluators instance...");
			instance = new BundleConfiguratorManager();
		}
	}

	private BundleConfiguratorManager() {
		super();
	}

	@Override
	public final List<BundleConfigurator> getAll() {
		return languages;
	}

	@Override
	public final void register(BundleConfigurator language) {
		logger.info("Register bundle configurator '"
				+ language.getClass().getName() + "'...");
		languages.add(language);
	}

	@Override
	public final void unregister(BundleConfigurator language) {
		logger.info("Unregister bundle configurator '"
				+ language.getClass().getName() + "'...");
		languages.remove(language);
	}

}
