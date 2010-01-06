package com.puresol.testing;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

public class StandardsTester {

	private static final Logger logger = Logger
			.getLogger(StandardsTester.class);

	public static boolean test(Class<?> clazz) {
		return new StandardsTester(clazz).test();
	}

	private Class<?> clazz;

	private StandardsTester(Class<?> clazz) {
		this.clazz = clazz;
	}

	private boolean test() {
		for (Field field : clazz.getDeclaredFields()) {
			if (!checkField(field)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkField(Field field) {
		Class<?> type = field.getType();
		int modifiers = field.getModifiers();
		String name = field.getName();
		if (type.equals(Logger.class)) {
			if (!name.equals("logger")) {
				logger.error("Field '" + name + "' in class '"
						+ clazz.getName() + "' should be named 'logger'!");
				return false;
			}
			if (!Modifier.isStatic(modifiers)) {
				logger.error("Field '" + name + "' in class '"
						+ clazz.getName() + "' should be declared as static!");
			}
		}
		if (type.equals(Translator.class)) {
			if (!name.equals("translator")) {
				logger.error("Field '" + name + "' in class '"
						+ clazz.getName() + "' should be named 'translator'!");
				return false;
			}
			if (!Modifier.isStatic(modifiers)) {
				logger.error("Field '" + name + "' in class '"
						+ clazz.getName() + "' should be declared as static!");
			}
		}
		return true;
	}
}
