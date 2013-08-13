package com.puresol.purifinity.coding.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.purifinity.coding.lang.test.ust.STARTCreator;
import com.puresol.purifinity.uhura.source.SourceFileLocation;
import com.puresol.purifinity.uhura.ust.USTCreatorFactory;

public class TestLanguageAnalyserTest {

	@BeforeClass
	public static void initialize() {
		USTCreatorFactory.initialize();
		USTCreatorFactory.register(STARTCreator.class.getPackage());
	}

	@AfterClass
	public static void destroy() {
		USTCreatorFactory.destroy();
	}

	@Test
	public void test() {
		assertNotNull(new TestLanguageAnalyser(new SourceFileLocation("", "")));
	}

	@Test
	public void testInitValues() {
		TestLanguageAnalyser analyser = new TestLanguageAnalyser(
				new SourceFileLocation(".", "TestFile.test"));
		assertEquals(new SourceFileLocation(".", "TestFile.test"),
				analyser.getSource());

	}
}
