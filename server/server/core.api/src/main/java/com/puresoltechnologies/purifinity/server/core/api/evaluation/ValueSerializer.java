package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;

public class ValueSerializer {

	public static String toString(Value<?> value) {
		Parameter<?> parameter = value.getParameter();
		Object valueObject = value.getValue();
		String name = parameter.getName();
		String unit = parameter.getUnit();
		String description = parameter.getDescription();
		Class<?> type = parameter.getType();
		LevelOfMeasurement levelOfMeasurement = parameter
				.getLevelOfMeasurement();
		Properties properties = new Properties();
		properties.setProperty("name", name);
		properties.setProperty("unit", unit);
		properties.setProperty("description", description);
		properties.setProperty("type", type.getName());
		properties.setProperty("levelOfMeasurement", levelOfMeasurement.name());
		if (valueObject != null) {
			properties.setProperty("value", valueObject.toString());
		}
		return PropertiesUtils.toString(properties);
	}

	public static Value<?> fromString(String string) {
		try {
			Properties properties = PropertiesUtils.fromString(string);
			String name = properties.getProperty("name");
			String unit = properties.getProperty("unit");
			String description = properties.getProperty("description");
			Class<?> type = Class.forName(properties.getProperty("type"));
			LevelOfMeasurement levelOfMeasurement = LevelOfMeasurement
					.valueOf(properties.getProperty("levelOfMeasurement"));
			String valueString = properties.getProperty("value");

			final Parameter<?> parameter = new ParameterWithArbitraryUnit<>(
					name, unit, levelOfMeasurement, description, type);

			// 1st trial: Does type have a static valueOf(String) method?
			try {
				Method method = type.getMethod("valueOf", String.class);
				Object value = method.invoke(null, valueString);
				return createValue(parameter, value);
			} catch (NoSuchMethodException e) {
				// intentionally left blank
			}
			// 2nd trial: Does type have a constructor(String) method?
			Constructor<?> stringConstructor = type
					.getConstructor(String.class);
			if (stringConstructor != null) {
				final Object value = stringConstructor.newInstance(valueString);
				return createValue(parameter, value);
			}
			// give up: Exception
			throw new RuntimeException("Could not deserialize:\n" + string);
		} catch (SecurityException | ClassNotFoundException
				| NoSuchMethodException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException("Could not deserialize:\n" + string, e);
		}
	}

	private static Value<Object> createValue(final Parameter<?> parameter,
			final Object value) {
		return new Value<Object>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object getValue() {
				return value;
			}

			@Override
			public Parameter<Object> getParameter() {
				@SuppressWarnings("unchecked")
				Parameter<Object> p = (Parameter<Object>) parameter;
				return p;
			}
		};
	}
}
