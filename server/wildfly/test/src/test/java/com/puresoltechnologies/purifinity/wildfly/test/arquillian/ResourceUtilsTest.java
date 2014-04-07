package com.puresoltechnologies.purifinity.wildfly.test.arquillian;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.purifinity.wildfly.test.arquillian.ResourceUtils;

public class ResourceUtilsTest {

	private static final String SAMPLE_RESOURCES_DIRECTORY = "target";

	private static File resourcesDirectory;

	@BeforeClass
	public static void initialize() {
		resourcesDirectory = new File(SAMPLE_RESOURCES_DIRECTORY);
		assertTrue("Directory '" + SAMPLE_RESOURCES_DIRECTORY
				+ "' was not found!", resourcesDirectory.exists());
	}

	@Test
	public void test() {
		List<File> resources = ResourceUtils.findResourcesRecursively(resourcesDirectory);
		assertNotNull(resources);
	}

}
