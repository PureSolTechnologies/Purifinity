package com.puresoltechnologies.purifinity.framework.lang.java7;

import org.osgi.framework.BundleContext;

import com.puresoltechnologies.commons.osgi.AbstractActivator;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.framework.lang.java7.Java;

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
		Java java = Java.getInstance();
		registerService(ProgrammingLanguageAnalyzer.class, java);
	}
}
