package com.puresol.coding.evaluator;

import java.util.List;

import org.apache.log4j.Logger;
import org.osgi.framework.InvalidSyntaxException;

import com.puresol.osgi.OSGiFrameworkManager;

public class ProjectEvaluatorManager {
	private static final Logger logger = Logger
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

}
