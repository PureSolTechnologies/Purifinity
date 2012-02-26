package com.puresol.coding.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.Activator;

public class CodeRangeEvaluatorManager {
    private static final Logger logger = LoggerFactory
	    .getLogger(CodeRangeEvaluatorManager.class);

    public static List<CodeRangeEvaluatorFactory> getAll() {
	try {
	    BundleContext context = Activator.getContext();
	    Collection<ServiceReference<CodeRangeEvaluatorFactory>> serviceReferences = context
		    .getServiceReferences(CodeRangeEvaluatorFactory.class, null);
	    List<CodeRangeEvaluatorFactory> services = new ArrayList<CodeRangeEvaluatorFactory>();
	    for (ServiceReference<CodeRangeEvaluatorFactory> serviceReference : serviceReferences) {
		services.add(context.getService(serviceReference));
	    }
	    return services;
	} catch (InvalidSyntaxException e) {
	    logger.error(e.getMessage(), e);
	    throw new RuntimeException(e.getMessage(), e);
	}
    }

}
