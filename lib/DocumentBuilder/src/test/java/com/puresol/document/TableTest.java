package com.puresol.document;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TableTest {

	@Test
	public void testInstance() {
		assertNotNull(new Table(null, "Test Table"));
	}

	@Test
	public void testInitialValues() {
		Table table = new Table(null, "Test Table", "Col1", "Col2");
		assertEquals("Test Table", table.getName());
		assertNull(table.getParent());
		assertFalse(table.hasChildren());
		assertEquals(0, table.getChildren().size());
		assertEquals(2, table.getColumnNum());
		assertEquals(0, table.getRowNum());
		assertEquals("Col1", table.getColumnNames().get(0));
		assertEquals("Col2", table.getColumnNames().get(1));
	}

	@Test
	public void testInitialValues2() {
		List<String> cols = new ArrayList<String>();
		cols.add("Col1");
		cols.add("Col2");
		Table table = new Table(null, "Test Table", cols);
		assertEquals("Test Table", table.getName());
		assertNull(table.getParent());
		assertFalse(table.hasChildren());
		assertEquals(0, table.getChildren().size());
		assertEquals(2, table.getColumnNum());
		assertEquals(0, table.getRowNum());
		assertEquals("Col1", table.getColumnNames().get(0));
		assertEquals("Col2", table.getColumnNames().get(1));
	}

	@Test
	public void testAddToDocument() {
		Document doc = new Document("Test Document");
		Table part = new Table(doc, "Test Table");
		assertSame(doc, part.getParent());
		assertFalse(part.hasChildren());
		assertEquals(0, part.getChildren().size());
	}

	@Test
	public void testAddRow() {
		Document doc = new Document("Test Document");
		Table table = new Table(doc, "Test Table", "Col1", "Col2");
		assertEquals(2, table.getColumnNum());
		assertEquals(0, table.getRowNum());
		table.addRow(1, 2);
		assertEquals(2, table.getColumnNum());
		assertEquals(1, table.getRowNum());
		List<Object> row = new ArrayList<Object>();
		row.add("3");
		row.add("4");
		table.addRow(row);
		assertEquals(2, table.getColumnNum());
		assertEquals(2, table.getRowNum());
		assertEquals(1, table.getRow(0).get(0));
		assertEquals(2, table.getRow(0).get(1));
		assertEquals("3", table.getRows().get(1).get(0));
		assertEquals("4", table.getRows().get(1).get(1));
	}

	@Test(expected = IllegalStateException.class)
	public void testAddIllegalRow() {
		Document doc = new Document("Test Document");
		Table table = new Table(doc, "Test Table", "Col1", "Col2");
		table.addRow(1);
	}

	@Test(expected = IllegalStateException.class)
	public void testAddIllegalRow2() {
		Document doc = new Document("Test Document");
		Table table = new Table(doc, "Test Table", "Col1", "Col2");
		List<Object> row = new ArrayList<Object>();
		row.add("3");
		table.addRow(row);
	}

	@Test
	public void testSetCaption() {
		Table part = new Table(null, "Test Table", "Col1", "Col2");
		part.setCaption("TestCaption");
		assertEquals("TestCaption", part.getCaption());
	}
}
