package com.puresol.uhura.parser.items;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.puresol.uhura.grammar.production.Production;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class LR0ItemTest {

	@Test
	public void testPersistence() {
		File file = new File("test", FileUtilities.classToRelativePackagePath(
				LR0Item.class).toString()
				+ ".persist");
		PersistenceTester.test(new LR0Item(new Production("Production"), 1),
				file);
	}

	@Test
	public void testInstance() {
		assertNotNull(new LR0Item(new Production("Production"), 1));
	}

	@Test
	public void testInitialValues() {
		Production production = new Production("Production");
		LR0Item item = new LR0Item(production, 1);
		assertSame(production, item.getProduction());
		assertEquals(1, item.getPosition());
	}

	@Test
	public void testGetters() {
		Production production = new Production("Production");
		LR0Item item = new LR0Item(production, 1);
		assertSame(production, item.getProduction());
		assertEquals(1, item.getPosition());
		assertNull(item.getNext());
		assertNull(item.get2ndNext());
		assertEquals(0, item.getBeta().getConstructions().size());
	}

}
