package com.puresol.uhura.parser.items;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class LR1ItemTest {

	@Test
	public void testPersistence() {
		File file = new File("test", FileUtilities.classToRelativePackagePath(
				LR1Item.class).toString()
				+ ".persist");
		PersistenceTester.test(new LR1Item(new Production("Production"), 1,
				new Terminal(".", null, false)), file);
	}

	@Test
	public void testInstance() {
		assertNotNull(new LR1Item(new Production("Production"), 1,
				new Terminal(".", null, false)));
	}

	@Test
	public void testInitialValues() {
		Production production = new Production("Production");
		Terminal construction = new Terminal(".", null, false);
		LR1Item item = new LR1Item(production, 1, construction);
		assertSame(production, item.getProduction());
		assertEquals(1, item.getPosition());
		assertSame(construction, item.getLookahead());
	}

	@Test
	public void testGetters() {
		Production production = new Production("Production");
		Terminal construction = new Terminal(".", null, false);
		LR1Item item = new LR1Item(production, 1, construction);
		assertSame(production, item.getProduction());
		assertEquals(1, item.getPosition());
		assertSame(construction, item.getLookahead());
		assertNull(item.getNext());
		assertNull(item.get2ndNext());
		assertEquals(0, item.getBeta().getConstructions().size());
	}

}
