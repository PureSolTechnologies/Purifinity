package com.puresoltechnologies.purifinity.server.common.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;

public class AbstractServiceTest {

	private static final File serviceDirectory = new File(
			"src/main/resources/META-INF/services");

	@BeforeClass
	public static void checkServicePresence() {
		assertTrue(serviceDirectory.exists());
	}

	@Test
	public void testServices() throws Exception {
		File[] serviceFiles = serviceDirectory.listFiles();
		for (File serviceFile : serviceFiles) {
			System.out.println("Found service file '" + serviceFile + "'.");
			checkServiceFile(serviceFile);
		}
	}

	private void checkServiceFile(File serviceFile) throws Exception {
		String interfaceName = serviceFile.getName();
		System.out.println("\tCheck interface '" + interfaceName
				+ "' for presence.");
		Class<?> clazz = Class.forName(interfaceName);
		assertNotNull(clazz);
		try (FileReader fileReader = new FileReader(serviceFile)) {
			try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				String className = bufferedReader.readLine();
				while (className != null) {
					System.out.println("\tCheck implementation '" + className
							+ "' for presence.");
					String classFileName = "/"
							+ className.replaceAll("\\.", "/") + ".class";
					URL classFileURL = getClass().getResource(classFileName);
					assertNotNull(classFileURL);
					System.out.println("\tFound implementation at '"
							+ classFileURL + "'.");
					className = bufferedReader.readLine();
				}
			}
		}
	}

}
