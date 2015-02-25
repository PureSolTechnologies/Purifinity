package com.puresoltechnologies.purifinity.server.common.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;

public class PropertiesUtilsTest {

	@Test
	public void testToString() {
		Properties properties = new Properties();
		properties.setProperty("key1", "value1");
		properties.setProperty("key2", "value2");
		properties.setProperty("key3", "value3");
		String string = PropertiesUtils.toString(properties);
		assertNotNull(string);
		assertEquals("key3=value3\nkey2=value2\nkey1=value1", string);
	}

	@Test
	public void testFromProperties() {
		String string = "key3=value3\nkey2=value2\nkey1=value1";
		Properties properties = PropertiesUtils.fromString(string);
		assertNotNull(properties);
		assertEquals("value1", properties.getProperty("key1"));
		assertEquals("value2", properties.getProperty("key2"));
		assertEquals("value3", properties.getProperty("key3"));
	}

	@Test
	public void testToAndFromString() {
		Properties properties = new Properties();
		properties.setProperty("key1", "value1");
		properties.setProperty("key2", "value2");
		properties.setProperty("key3", "value3");
		String string = PropertiesUtils.toString(properties);
		assertNotNull(string);
		Properties propertiesNew = PropertiesUtils.fromString(string);
		assertNotNull(propertiesNew);
		assertEquals(properties, propertiesNew);
		String stringNew = PropertiesUtils.toString(propertiesNew);
		assertNotNull(stringNew);
		assertEquals(string, stringNew);
	}

}
