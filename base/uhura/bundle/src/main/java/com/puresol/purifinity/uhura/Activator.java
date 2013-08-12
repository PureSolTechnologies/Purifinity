package com.puresol.purifinity.uhura;

import org.osgi.framework.BundleContext;

import com.puresol.commons.osgi.AbstractActivator;
import com.puresol.purifinity.uhura.ust.USTCreatorFactory;

public class Activator extends AbstractActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		USTCreatorFactory.initialize();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		USTCreatorFactory.destroy();
		super.stop(context);
	}

}
