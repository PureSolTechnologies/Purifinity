package com.puresoltechnologies.purifinity.framework.lang.c11;

import org.osgi.framework.BundleContext;

import com.puresoltechnologies.commons.osgi.AbstractActivator;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.framework.lang.c11.C11;

/**
 * This class is used as OSGi bundle activator. This class only registers and
 * unregisters itself int the central ProgrammingLanguages object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Activator extends AbstractActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		C11 c11 = C11.getInstance();
		registerService(ProgrammingLanguageAnalyzer.class, c11);
	}

}
