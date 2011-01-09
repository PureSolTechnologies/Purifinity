package com.puresol.coding;

import java.util.List;

import org.apache.log4j.Logger;
import org.osgi.framework.InvalidSyntaxException;

import com.puresol.osgi.OSGiFrameworkManager;

public class ProgrammingLanguages {

	private static final Logger logger = Logger
			.getLogger(ProgrammingLanguages.class);

	public static List<ProgrammingLanguage> getAll() {
		try {
			return OSGiFrameworkManager.getServices(
					ProgrammingLanguage.class.getName(), "(objectClass="
							+ ProgrammingLanguage.class.getName() + ")");
		} catch (InvalidSyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
