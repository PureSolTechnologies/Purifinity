package com.puresol.reporting.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.reporting.html.Entities;

public class EntityTest {

	@Test
	public void testOrder() {
		Entities[] entities = Entities.class.getEnumConstants();
		assertEquals(Entities.AMP, entities[0]);
		assertEquals(Entities.LT, entities[1]);
		assertEquals(Entities.GT, entities[2]);
		assertEquals(Entities.QUOT, entities[3]);
		assertEquals(Entities.UUML, entities[4]);
		assertEquals(Entities.UUML_C, entities[5]);
		assertEquals(Entities.OUML, entities[6]);
		assertEquals(Entities.OUML_C, entities[7]);
		assertEquals(Entities.AUML, entities[8]);
		assertEquals(Entities.AUML_C, entities[9]);
		assertEquals(Entities.SZLIG, entities[10]);
	}

	@Test
	public void testToEntity() {
		assertEquals(
				"&lt;&gt;&amp;&quot;&Uuml;&uuml;&Ouml;&ouml;&Auml;&auml;&szlig;",
				Entities.convertToEntities("<>&\"ÜüÖöÄäß"));
	}
}
