package com.puresol.purifinity.uhura.ust;

import org.reflections.scanners.AbstractScanner;
import org.reflections.scanners.SubTypesScanner;

/**
 * This class is an implementation of a recursive sub types scanner as
 * implemented in {@link SubTypesScanner}. The original {@link SubTypesScanner}
 * does not check for recursive interfaces and super classes.
 * 
 * @author Rick-Rainer Ludwig
 */
public class RecursiveSubTypesScanner extends AbstractScanner {

	@Override
	public void scan(Object cls) {
		try {
			@SuppressWarnings("unchecked")
			String className = getMetadataAdapter().getClassName(cls);
			Class<?> clazz = Class.forName(className);
			scan(clazz, className);
		} catch (ClassNotFoundException e) {
			return;
		}
	}

	private void scan(Class<?> clazz, String className) {
		Class<?> superClass = clazz.getSuperclass();

		if (acceptResult(superClass.getName())) {
			getStore().put(superClass.getName(), className);
		}
		for (Class<?> anInterface : clazz.getInterfaces()) {
			if (acceptResult(anInterface.getName())) {
				getStore().put(anInterface.getName(), className);
			}
		}
		if (superClass != null) {
			scan(superClass, className);
		}
	}
}
