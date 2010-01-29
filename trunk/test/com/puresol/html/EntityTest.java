package com.puresol.html;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class EntityTest extends TestCase {

    @Test
    public void testOrder() {
	Entities[] entities = Entities.class.getEnumConstants();
	Assert.assertEquals(Entities.AMP, entities[0]);
	Assert.assertEquals(Entities.LT, entities[1]);
	Assert.assertEquals(Entities.GT, entities[2]);
	Assert.assertEquals(Entities.QUOT, entities[3]);
	Assert.assertEquals(Entities.UUML, entities[4]);
	Assert.assertEquals(Entities.UUML_C, entities[5]);
	Assert.assertEquals(Entities.OUML, entities[6]);
	Assert.assertEquals(Entities.OUML_C, entities[7]);
	Assert.assertEquals(Entities.AUML, entities[8]);
	Assert.assertEquals(Entities.AUML_C, entities[9]);
	Assert.assertEquals(Entities.SZLIG, entities[10]);
    }

    @Test
    public void testToEntity() {
	Assert
		.assertEquals(
			"&lt;&gt;&amp;&quot;&Uuml;&uuml;&Ouml;&ouml;&Auml;&auml;&szlig;",
			Entities.convertToEntities("<>&\"ÜüÖöÄäß"));
    }
}
