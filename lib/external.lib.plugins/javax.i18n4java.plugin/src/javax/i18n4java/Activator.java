package javax.i18n4java;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static final Translator translator = Translator
			.getTranslator(Activator.class);

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println(translator.i18n("Starting i18n4java.bundle..."));
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println(translator.i18n("Stopping i18n4java.bundle..."));
	}

}
