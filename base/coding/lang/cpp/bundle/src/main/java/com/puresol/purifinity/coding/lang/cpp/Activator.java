package com.puresol.purifinity.coding.lang.cpp;

import org.osgi.framework.BundleContext;

import com.puresol.commons.osgi.AbstractActivator;
import com.puresol.purifinity.coding.analysis.api.AnalyzableProgrammingLanguage;

public class Activator extends AbstractActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		CPP cpp = CPP.getInstance();
		registerService(AnalyzableProgrammingLanguage.class, cpp);
	}

}
