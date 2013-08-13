package com.puresol.purifinity.coding.lang.fortran;

import org.osgi.framework.BundleContext;

import com.puresol.commons.osgi.AbstractActivator;
import com.puresol.purifinity.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.purifinity.coding.lang.fortran.ust.ProgramCreator;
import com.puresol.purifinity.uhura.ust.USTCreatorFactory;

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
		USTCreatorFactory.register(ProgramCreator.class.getPackage());
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		USTCreatorFactory.unregister(ProgramCreator.class.getPackage());
		super.stop(context);
	}

}
