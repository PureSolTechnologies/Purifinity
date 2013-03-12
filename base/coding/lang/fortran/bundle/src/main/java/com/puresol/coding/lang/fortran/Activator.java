package com.puresol.coding.lang.fortran;

import org.osgi.framework.BundleContext;

import com.puresol.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.commons.osgi.AbstractActivator;

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
	Fortran fortran = Fortran.getInstance();
	registerService(AnalyzableProgrammingLanguage.class, fortran);
    }

}
