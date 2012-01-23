package com.puresol.coding.evaluator;

import java.util.List;

import org.osgi.framework.InvalidSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.osgi.OSGiFrameworkManager;

public class CodeRangeEvaluatorManager {
    private static final Logger logger = LoggerFactory
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
