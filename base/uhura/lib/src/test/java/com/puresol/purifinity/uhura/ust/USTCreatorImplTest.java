package com.puresol.purifinity.uhura.ust;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class USTCreatorImplTest {

	@Test
	public void test() throws Exception {
		USTCreatorImpl impl = new USTCreatorImpl(
				UniversalSyntaxTree.class.getPackage());
		Field field = impl.getClass().getDeclaredField("classes");
		field.setAccessible(true);
		@SuppressWarnings("unchecked")
		Map<String, Class<?>> object = (Map<String, Class<?>>) field.get(impl);
		Set<String> keys = object.keySet();
		keys.contains("Expression");
		keys.contains("Addition");
		assertTrue(keys.size() > 0);
	}

}
