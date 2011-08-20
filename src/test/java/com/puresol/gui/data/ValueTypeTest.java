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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ValueTypeTest {

	@Test
	public void testRecognizeType() {
		assertEquals(Boolean.class, ValueType.recognizeFromString("true")
				.getClassObject());
		assertEquals(Boolean.class, ValueType.recognizeFromString("false")
				.getClassObject());

		assertEquals(Byte.class, ValueType.recognizeFromString("-128")
				.getClassObject());
		assertEquals(Byte.class, ValueType.recognizeFromString("0")
				.getClassObject());
		assertEquals(Byte.class, ValueType.recognizeFromString("127")
				.getClassObject());

		assertEquals(Short.class, ValueType.recognizeFromString("-32768")
				.getClassObject());
		assertEquals(Short.class, ValueType.recognizeFromString("-129")
				.getClassObject());
		assertEquals(Short.class, ValueType.recognizeFromString("128")
				.getClassObject());
		assertEquals(Short.class, ValueType.recognizeFromString("32767")
				.getClassObject());

		assertEquals(Integer.class, ValueType
				.recognizeFromString("-2147483648").getClassObject());
		assertEquals(Integer.class, ValueType.recognizeFromString("-32769")
				.getClassObject());
		assertEquals(Integer.class, ValueType.recognizeFromString("32768")
				.getClassObject());
		assertEquals(Integer.class, ValueType.recognizeFromString("2147483647")
				.getClassObject());

		assertEquals(Long.class, ValueType.recognizeFromString("-2147483649")
				.getClassObject());
		assertEquals(Long.class, ValueType.recognizeFromString("2147483648")
				.getClassObject());

		assertEquals(Float.class, ValueType.recognizeFromString("-1.1E+38")
				.getClassObject());
		assertEquals(Float.class, ValueType.recognizeFromString("-1.1")
				.getClassObject());
		assertEquals(Float.class, ValueType.recognizeFromString("0.0")
				.getClassObject());
		assertEquals(Float.class, ValueType.recognizeFromString("1.1")
				.getClassObject());
		assertEquals(Float.class, ValueType.recognizeFromString("1.1E+38")
				.getClassObject());

		assertEquals(Double.class, ValueType.recognizeFromString("-1.1E+308")
				.getClassObject());
		assertEquals(Double.class, ValueType.recognizeFromString("-1.1E+39")
				.getClassObject());
		assertEquals(Double.class, ValueType.recognizeFromString("1.1E+39")
				.getClassObject());
		assertEquals(Double.class, ValueType.recognizeFromString("1.1E+308")
				.getClassObject());

		assertEquals(Character.class, ValueType.recognizeFromString("C")
				.getClassObject());

		assertEquals(String.class, ValueType.recognizeFromString("-1.1E+309")
				.getClassObject());
		assertEquals(String.class, ValueType.recognizeFromString("1.1E+309")
				.getClassObject());
	}

	@Test
	public void testSort() {
		List<ValueType> list = new ArrayList<ValueType>();
		list.add(ValueType.fromClass(String.class));
		list.add(ValueType.fromClass(Byte.class));
		list.add(ValueType.fromClass(Long.class));
		list.add(ValueType.fromClass(Integer.class));
		list.add(ValueType.fromClass(Short.class));
		list.add(ValueType.fromClass(Double.class));
		list.add(ValueType.fromClass(Float.class));
		list.add(ValueType.fromClass(Character.class));
		list.add(ValueType.fromClass(Boolean.class));
		Collections.sort(list);
		assertEquals(Byte.class, list.get(0).getClassObject());
		assertEquals(Short.class, list.get(1).getClassObject());
		assertEquals(Integer.class, list.get(2).getClassObject());
		assertEquals(Long.class, list.get(3).getClassObject());
		assertEquals(Float.class, list.get(4).getClassObject());
		assertEquals(Double.class, list.get(5).getClassObject());
		assertEquals(Character.class, list.get(6).getClassObject());
		assertEquals(Boolean.class, list.get(7).getClassObject());
		assertEquals(String.class, list.get(8).getClassObject());
	}
}
