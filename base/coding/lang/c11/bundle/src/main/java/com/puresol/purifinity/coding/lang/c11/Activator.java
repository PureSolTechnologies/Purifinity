package com.puresol.purifinity.coding.lang.c11;

import org.osgi.framework.BundleContext;

import com.puresol.purifinity.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.purifinity.coding.lang.c11.C11;
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
	C11 c11 = C11.getInstance();
	registerService(AnalyzableProgrammingLanguage.class, c11);
    }

}
