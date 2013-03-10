package com.puresol.coding.client.lang.java6;

import org.osgi.framework.BundleContext;

import com.puresol.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.coding.client.common.osgi.AbstractActivator;
import com.puresol.coding.lang.java.Java;

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
