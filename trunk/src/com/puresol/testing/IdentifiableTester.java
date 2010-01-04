package com.puresol.testing;

import org.apache.log4j.Logger;

import com.puresol.data.Identifiable;

public class IdentifiableTester {

	private static final Logger logger = Logger
			.getLogger(IdentifiableTester.class);

	public static boolean test(Class<? extends Identifiable> clazz) {
		return new IdentifiableTester(clazz).test();
	}

	private Class<? extends Identifiable> clazz;

	private IdentifiableTester(Class<? extends Identifiable> clazz) {
		this.clazz = clazz;
	}

	private boolean test() {
		Identifiable[] constants = clazz.getEnumConstants();
		for (Identifiable constant : constants) {
			String identifier = constant.getIdentifier();
			if (identifier == null) {
				logger.error("Identifier is null in ("
						+ constant.getClass().getName() + ")!");
			}
			if (identifier.isEmpty()) {
				logger.error("Identifier is empty in ("
						+ constant.getClass().getName() + ")!");
			}
		}
		return true;
	}
}
