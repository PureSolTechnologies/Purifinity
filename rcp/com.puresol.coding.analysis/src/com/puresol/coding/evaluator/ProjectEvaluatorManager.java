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

public class ProjectEvaluatorManager {
    private static final Logger logger = LoggerFactory
	    .getLogger(ProjectEvaluatorManager.class);

    public static List<ProjectEvaluatorFactory> getAll() {
	try {
	    BundleContext context = Activator.getContext();
	    Collection<ServiceReference<ProjectEvaluatorFactory>> serviceReferences = context
		    .getServiceReferences(ProjectEvaluatorFactory.class, null);
	    List<ProjectEvaluatorFactory> services = new ArrayList<ProjectEvaluatorFactory>();
	    for (ServiceReference<ProjectEvaluatorFactory> serviceReference : serviceReferences) {
		services.add(context.getService(serviceReference));
	    }
	    return services;
	} catch (InvalidSyntaxException e) {
	    logger.error(e.getMessage(), e);
	    throw new RuntimeException(e.getMessage(), e);
	}
    }

    public static List<Result> getAvailableResults(Evaluator evaluator) {
	return evaluator.getResults();
    }

}
