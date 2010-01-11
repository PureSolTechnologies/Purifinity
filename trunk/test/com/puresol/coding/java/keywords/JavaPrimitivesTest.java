package com.puresol.coding.java.keywords;

import java.util.Vector;

import junit.framework.Assert;

import org.junit.Test;

import com.puresol.coding.tokentypes.PrimitiveDataType;
import com.puresol.introspect.ExtentedPackage;

public class JavaPrimitivesTest {

	@Test
	public void testPrimitives() {
		try {
			Class<?>[] classes = ExtentedPackage
					.getClassesWithSuperclass(
							"com.puresol.coding.java.keywords",
							PrimitiveDataType.class);
			Assert.assertEquals(8, classes.length);
			Vector<Class<?>> classVector = new Vector<Class<?>>();
			for (Class<?> clazz : classes) {
				classVector.add(clazz);
			}
			Assert.assertTrue(classVector.contains(BooleanKeyword.class));
			Assert.assertTrue(classVector.contains(CharKeyword.class));
			Assert.assertTrue(classVector.contains(ByteKeyword.class));
			Assert.assertTrue(classVector.contains(ShortKeyword.class));
			Assert.assertTrue(classVector.contains(IntKeyword.class));
			Assert.assertTrue(classVector.contains(LongKeyword.class));
			Assert.assertTrue(classVector.contains(FloatKeyword.class));
			Assert.assertTrue(classVector.contains(DoubleKeyword.class));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
