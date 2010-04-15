package com.puresol.utils.di;

import java.lang.reflect.Field;

public class DependencyInjection {

	public static <T> T inject(T client, Object... injections) {
		Class<?> clazz = client.getClass();
		do {
			inject(clazz, client, injections);
			clazz = clazz.getSuperclass();
		} while (clazz != null);
		return client;
	}

	public static <T> T inject(Class<?> clazz, T client, Object... injections) {
		Field[] fields = clazz.getDeclaredFields();
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
			Class<?> fieldType = field.getType();
			for (Object injection : injections) {
				if (injection != null) {
					if (injection.getClass().equals(fieldType)) {
						field.setAccessible(true);
						field.set(client, injection);
						field.setAccessible(false);
						return;
					}
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
