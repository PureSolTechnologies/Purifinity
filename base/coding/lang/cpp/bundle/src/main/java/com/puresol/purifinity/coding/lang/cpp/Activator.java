package com.puresol.purifinity.coding.lang.cpp;

import org.osgi.framework.BundleContext;

import com.puresol.purifinity.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.purifinity.coding.lang.cpp.CPP;
import com.puresol.purifinity.commons.osgi.AbstractActivator;

public class Activator extends AbstractActivator {

    @Override
    public void start(BundleContext context) throws Exception {
	super.start(context);
	CPP cpp = CPP.getInstance();
	registerService(AnalyzableProgrammingLanguage.class, cpp);
    }

}
