package com.puresol.coding.evaluator;

import java.util.List;

import org.osgi.framework.InvalidSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.osgi.OSGiFrameworkManager;

public class ProjectEvaluatorManager {
    private static final Logger logger = LoggerFactory
	    .getLogger(ProjectEvaluatorManager.class);

    public static List<ProjectEvaluatorFactory> getAll() {
	try {
	    return OSGiFrameworkManager.getServices(
		    ProjectEvaluatorFactory.class.getName(), "(objectClass="
			    + ProjectEvaluatorFactory.class.getName() + ")");
	} catch (InvalidSyntaxException e) {
	    logger.error(e.getMessage(), e);
	    throw new RuntimeException(e.getMessage(), e);
	}
    }

    public static List<Result> getAvailableResults(Evaluator evaluator) {
	return evaluator.getResults();
    }

}
