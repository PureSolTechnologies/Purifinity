package com.puresol.coding.evaluator;

import java.util.List;

import org.apache.log4j.Logger;
import org.osgi.framework.InvalidSyntaxException;

import com.puresol.osgi.OSGiFrameworkManager;

public class CodeRangeEvaluatorManager {
	private static final Logger logger = Logger
			.getLogger(CodeRangeEvaluatorManager.class);

	public static List<CodeRangeEvaluatorFactory> getAll() {
		try {
			return OSGiFrameworkManager.getServices(
					CodeRangeEvaluatorFactory.class.getName(), "(objectClass="
							+ CodeRangeEvaluatorFactory.class.getName() + ")");
		} catch (InvalidSyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}