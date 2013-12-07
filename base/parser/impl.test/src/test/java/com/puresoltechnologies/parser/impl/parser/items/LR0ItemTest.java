package com.puresoltechnologies.parser.impl.parser.items;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.parser.impl.grammar.production.Production;
import com.puresoltechnologies.parser.impl.parser.items.LR0Item;

public class LR0ItemTest {

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
