package com.puresoltechnologies.purifinity.coding.lang.cpp11;

import org.osgi.framework.BundleContext;

import com.puresoltechnologies.commons.osgi.AbstractActivator;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.coding.lang.cpp11.CPP;

public class Activator extends AbstractActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		CPP cpp = CPP.getInstance();
		registerService(ProgrammingLanguageAnalyzer.class, cpp);
	}

}
