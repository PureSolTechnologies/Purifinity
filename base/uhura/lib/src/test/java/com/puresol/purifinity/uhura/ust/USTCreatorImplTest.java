package com.puresol.purifinity.uhura.ust;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.purifinity.uhura.ust.test.CompilationUnitCreator;

public class USTCreatorImplTest {

	private static USTCreatorImpl impl;

	private static Map<String, Class<?>> classes;

	@BeforeClass
	@SuppressWarnings("unchecked")
	public static void initialized() throws Exception {
		impl = new USTCreatorImpl(CompilationUnitCreator.class.getPackage());

		Field field = impl.getClass().getDeclaredField("classes");
		field.setAccessible(true);
		classes = (Map<String, Class<?>>) field.get(impl);
	}

	@Test
	public void test() {
		Set<String> keys = classes.keySet();
		assertTrue(keys.contains("CompilationUnitCreator"));
		assertTrue(keys.size() > 0);
	}

}
