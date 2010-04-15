package com.puresol.utils;

import java.lang.reflect.Field;

public class DependencyInjection {

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
