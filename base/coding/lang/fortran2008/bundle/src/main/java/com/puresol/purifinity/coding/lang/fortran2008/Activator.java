package com.puresol.purifinity.coding.lang.fortran2008;

import org.osgi.framework.BundleContext;

import com.puresol.commons.osgi.AbstractActivator;
import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresol.purifinity.coding.lang.fortran2008.Fortran;

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
		registerService(ProgrammingLanguageAnalyzer.class, fortran);
	}

}