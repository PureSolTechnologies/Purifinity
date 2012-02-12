package javax.i18n4java;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator {

	private static final Logger logger = LoggerFactory
			.getLogger(Activator.class);
	private static final Translator translator = Translator
			.getTranslator(Activator.class);

	@Override
	public void start(BundleContext context) throws Exception {
		logger.info(translator.i18n("Starting i18n4java.bundle..."));
		System.out.println(translator.i18n("Starting i18n4java.bundle..."));
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info(translator.i18n("Stopping i18n4java.bundle..."));
		System.out.println(translator.i18n("Stopping i18n4java.bundle..."));
	}

}
