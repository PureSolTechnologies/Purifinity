package org.apache.cassandra.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;

import org.apache.cassandra.server.CassandraConfiguration;
import org.junit.Test;

public class CassandraConfigurationTest {

	@Test
	public void testVariablePattern() {
		assertTrue(CassandraConfiguration.pattern.matcher("${a}").matches());
		assertTrue(CassandraConfiguration.pattern.matcher("${a.a}").matches());
		assertTrue(CassandraConfiguration.pattern.matcher("${a-a}").matches());
		assertTrue(CassandraConfiguration.pattern.matcher("${a_a}").matches());
		assertFalse(CassandraConfiguration.pattern.matcher("${{a}}").matches());
	}

	@Test
	public void testVariableSubstitution() {
		Matcher matcher = CassandraConfiguration.pattern
				.matcher("BlaBlaBla ${test1} ${test2} end...");
		assertFalse(matcher.matches());
		assertTrue(matcher.find());
		assertEquals(1, matcher.groupCount());
		assertEquals("${test1}", matcher.group(0));
		assertEquals("test1", matcher.group(1));
		assertTrue(matcher.find());
		assertEquals(1, matcher.groupCount());
		assertEquals("${test2}", matcher.group(0));
		assertEquals("test2", matcher.group(1));
	}
}
