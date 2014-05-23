package com.puresoltechnologies.purifinity.server.core.impl.analysis.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;

public class SourceCodeLocationCreator {

	public static SourceCodeLocation createFromSerialization(
			Properties properties) throws IllegalArgumentException {
		String repositoryLocationClass = properties
				.getProperty(SourceCodeLocation.SOURCE_CODE_LOCATION_CLASS);
		try {
			Class<?> repositoryClass = Class.forName(repositoryLocationClass);
			Constructor<?> repositoryConstructor = repositoryClass
					.getConstructor(Properties.class);
			SourceCodeLocation repositoryLocation = (SourceCodeLocation) repositoryConstructor
					.newInstance(properties);
			return repositoryLocation;
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(
					"Unknown repository location for class '"
							+ repositoryLocationClass + "'.", e);
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			throw new IllegalArgumentException(
					"Repository location for class '" + repositoryLocationClass
							+ "' could not be instantiated.", e);
		}
	}
}
