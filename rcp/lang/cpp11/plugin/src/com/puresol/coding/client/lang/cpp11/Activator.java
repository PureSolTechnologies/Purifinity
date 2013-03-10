package com.puresol.coding.client.lang.cpp11;

import org.osgi.framework.BundleContext;

import com.puresol.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.coding.client.common.osgi.AbstractActivator;
import com.puresol.coding.lang.cpp.CPP;

public class Activator extends AbstractActivator {

    @Override
    public void start(BundleContext context) throws Exception {
	super.start(context);
	CPP cpp = CPP.getInstance();
	registerService(AnalyzableProgrammingLanguage.class, cpp);
    }
}
