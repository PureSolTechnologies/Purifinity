/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.gui.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class TypeWrapperTest {

	@Test
	public void testPrimitiveAndWrapperConfig() {
		assertEquals(Integer.valueOf(8),
				Integer.valueOf(TypeWrapper.PRIMITIVES.length));
		assertEquals(Integer.valueOf(8),
				Integer.valueOf(TypeWrapper.PRIMITIVE_WRAPPERS.length));
	}

	@Test
	public void testIsPrimitiveWrapper() {
		assertTrue(TypeWrapper.isPrimitiveWrapper(Byte.class));
		assertTrue(TypeWrapper.isPrimitiveWrapper(Short.class));
		assertTrue(TypeWrapper.isPrimitiveWrapper(Integer.class));
		assertTrue(TypeWrapper.isPrimitiveWrapper(Long.class));
		assertTrue(TypeWrapper.isPrimitiveWrapper(Float.class));
		assertTrue(TypeWrapper.isPrimitiveWrapper(Double.class));
		assertTrue(TypeWrapper.isPrimitiveWrapper(Character.class));
		assertTrue(TypeWrapper.isPrimitiveWrapper(Boolean.class));
	}

	@Test
	public void testIsPrimitiveOrWrapper() {
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(Byte.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(Short.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(Integer.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(Long.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(Float.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(Double.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(Character.class));
		assertTrue(TypeWrapper.isPrimitiveWrapper(Boolean.class));

		assertTrue(TypeWrapper.isPrimitiveOrWrapper(byte.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(short.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(int.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(long.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(float.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(double.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(char.class));
		assertTrue(TypeWrapper.isPrimitiveOrWrapper(boolean.class));
	}

	@Test
	public void testToPrimitive() {
		assertEquals(byte.class, TypeWrapper.toPrimitive(Byte.class));
		assertEquals(short.class, TypeWrapper.toPrimitive(Short.class));
		assertEquals(int.class, TypeWrapper.toPrimitive(Integer.class));
		assertEquals(long.class, TypeWrapper.toPrimitive(Long.class));
		assertEquals(float.class, TypeWrapper.toPrimitive(Float.class));
		assertEquals(double.class, TypeWrapper.toPrimitive(Double.class));
		assertEquals(char.class, TypeWrapper.toPrimitive(Character.class));
		assertEquals(boolean.class, TypeWrapper.toPrimitive(Boolean.class));
	}

	@Test
	public void testToPrimitiveWrapper() {
		assertEquals(Byte.class, TypeWrapper.toPrimitiveWrapper(byte.class));
		assertEquals(Short.class, TypeWrapper.toPrimitiveWrapper(short.class));
		assertEquals(Integer.class, TypeWrapper.toPrimitiveWrapper(int.class));
		assertEquals(Long.class, TypeWrapper.toPrimitiveWrapper(long.class));
		assertEquals(Float.class, TypeWrapper.toPrimitiveWrapper(float.class));
		assertEquals(Double.class, TypeWrapper.toPrimitiveWrapper(double.class));
		assertEquals(Character.class,
				TypeWrapper.toPrimitiveWrapper(char.class));
		assertEquals(Boolean.class,
				TypeWrapper.toPrimitiveWrapper(boolean.class));
	}
}
