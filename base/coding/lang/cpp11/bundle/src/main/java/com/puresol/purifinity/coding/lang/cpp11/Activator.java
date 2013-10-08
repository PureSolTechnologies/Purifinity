package com.puresol.purifinity.coding.lang.cpp11;

import org.osgi.framework.BundleContext;

import com.puresol.commons.osgi.AbstractActivator;
import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguageAnalyzer;

public class Activator extends AbstractActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		CPP cpp = CPP.getInstance();
		registerService(ProgrammingLanguageAnalyzer.class, cpp);
	}

}
