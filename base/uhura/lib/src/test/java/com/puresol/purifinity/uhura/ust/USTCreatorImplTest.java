package com.puresol.purifinity.uhura.ust;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.purifinity.uhura.ust.test.STARTCreator;

public class USTCreatorImplTest {

	private static USTCreatorImpl impl;

	@BeforeClass
	public static void initialized() {
		impl = new USTCreatorImpl(STARTCreator.class);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void test() throws Exception {
		Field field = impl.getClass().getDeclaredField("classes");
		field.setAccessible(true);
		Map<String, Class<?>> classes = (Map<String, Class<?>>) field.get(impl);
		Set<String> keys = classes.keySet();
		assertTrue(keys.contains("STARTCreator"));
		assertTrue(keys.size() > 0);
	}

}
