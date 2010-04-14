package com.puresol.utils;

import java.lang.reflect.Field;

public class DependencyInjection {

	public static class ClassBuilder<T> {

		private final Class<T> clazz;
		private final Object parameter[];
		private Object injections[];

		public ClassBuilder(Class<T> clazz, Object... parameter) {
			this.clazz = clazz;
			this.parameter = parameter;
		}

		public void setInjection(Object... injections) {
			this.injections = injections;
		}

		public T createInstance() throws ClassInstantiationException {
			T instance = Instances.createInstance(clazz, parameter);
			@SuppressWarnings("unchecked")
			T instanceT = (T) DependencyInjection.inject(instance, injections);
			return instanceT;

		}
	}

	public static Object inject(Object client, Object... injections) {
		Field[] fields = client.getClass().getDeclaredFields();
		for (Field field : fields) {
			Inject inject = field.getAnnotation(Inject.class);
			if (inject != null) {
				inject(field, inject, client, injections);
			}
		}
		return client;
	}

	private static void inject(Field field, Inject inject, Object client,
			Object... injections) {
		try {
			for (Object injection : injections) {
				if (injection.getClass().equals(inject.value())) {
					field.setAccessible(true);
					field.set(client, injection);
					field.setAccessible(false);
					return;
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
