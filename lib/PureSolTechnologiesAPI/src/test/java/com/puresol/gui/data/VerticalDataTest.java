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

public class VerticalDataTest {

	@Test
	public void testConstructor() {
		VerticalData data = new VerticalData();
		assertEquals(Integer.valueOf(0),
				Integer.valueOf(data.getColumnNumber()));
		assertEquals(Integer.valueOf(0), Integer.valueOf(data.getRowNumber()));
	}

	@Test
	public void testSettersAndGetters() {
		VerticalData data = new VerticalData();
		data.addColumn("String", String.class);
		data.addColumn("Integer", Integer.class);
		assertEquals(Integer.valueOf(0),
				Integer.valueOf(data.getColumnID("String")));
		assertEquals(Integer.valueOf(1),
				Integer.valueOf(data.getColumnID("Integer")));
		data.addRow("Row1", 1);
		data.addRow("Row2", 2);
		assertEquals(Integer.valueOf(0),
				Integer.valueOf(data.getColumnID("String")));
		assertEquals(Integer.valueOf(1),
				Integer.valueOf(data.getColumnID("Integer")));
		assertEquals("Row1", data.getString(0, 0));
		assertEquals("Row2", data.getString(1, 0));
		assertEquals(Integer.valueOf(1), Integer.valueOf(data.getInteger(0, 1)));
		assertEquals(Integer.valueOf(2), Integer.valueOf(data.getInteger(1, 1)));
	}

	@Test
	public void testTynamicalAssignments() {
		VerticalData data = new VerticalData();
		data.addColumn("String", String.class);
		data.addColumn("Integer", Integer.class);
		data.addColumn("Double", Double.class);
		data.addRow("S", "1", "1.1");
		assertEquals("S", data.getString(0, 0));
		assertEquals(Integer.valueOf(1), Integer.valueOf(data.getInteger(0, 1)));
		assertEquals(Double.valueOf(1.1), data.getDouble(0, 2));
	}
}
