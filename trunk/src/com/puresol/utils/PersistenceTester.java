package com.puresol.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class PersistenceTester {

	public static void test(Object o, File file) {
		try {
			assertTrue(
					"Serializable is not implemented in class '" + o.getClass()
							+ "'!",
					Serializable.class.isAssignableFrom(o.getClass()));
			if (file.exists()) {
				assertTrue(file.delete());
			}
			assertFalse(file.exists());
			Persistence.persist(o, file);
			assertTrue(file.exists());
			Object restored = Persistence.restore(file);
			assertNotNull(restored);
			assertNotSame(restored, o);
			assertTrue(file.delete());
			assertFalse(file.exists());
		} catch (PersistenceException e) {
			e.printStackTrace();
			fail("Could not persist object!");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Could not persist object!");
		}
	}
}
