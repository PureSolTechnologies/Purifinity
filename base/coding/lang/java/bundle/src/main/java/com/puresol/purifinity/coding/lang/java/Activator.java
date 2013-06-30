package com.puresol.purifinity.coding.lang.java;

import org.osgi.framework.BundleContext;

import com.puresol.purifinity.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.purifinity.coding.lang.java.Java;
import com.puresol.purifinity.commons.osgi.AbstractActivator;

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
	registerService(AnalyzableProgrammingLanguage.class, java);
    }

}