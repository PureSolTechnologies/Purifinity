package com.puresol.coding.lang.cpp;

import org.osgi.framework.BundleContext;

import com.puresol.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.commons.osgi.AbstractActivator;

public class Activator extends AbstractActivator {

    @Override
    public void start(BundleContext context) throws Exception {
	super.start(context);
	CPP cpp = CPP.getInstance();
	registerService(AnalyzableProgrammingLanguage.class, cpp);
    }

}
