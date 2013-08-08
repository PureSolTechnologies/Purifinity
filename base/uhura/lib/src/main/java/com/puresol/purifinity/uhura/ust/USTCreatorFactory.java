package com.puresol.purifinity.uhura.ust;

import java.util.HashMap;
import java.util.Map;

public class USTCreatorFactory {

	private static final Map<Package, USTCreator> creators = new HashMap<Package, USTCreator>();

	private static boolean initialized = false;

	public static void initialize() {
		synchronized (creators) {
			assertNotInitialized();
			initialized = true;
		}
	}

	public static void destroy() {
		synchronized (creators) {
			assertInitialized();
			initialized = false;
			creators.clear();
		}
	}

	public void register(Package pkg) {
		assertInitialized();
		creators.put(pkg, new USTCreatorImpl(pkg));
	}

	public void unregister(Package pkg) {
		assertInitialized();
		creators.remove(pkg);
	}

	public static USTCreator create(Package pkg) {
		assertInitialized();
		return creators.get(pkg);
	}

	private static void assertInitialized() {
		if (!initialized) {
			throw new IllegalArgumentException("Not initialized, yet!");
		}
	}

	private static void assertNotInitialized() {
		if (initialized) {
			throw new IllegalArgumentException("Already initialized!");
		}
	}

	/**
	 * Private constructor to avoid instantiation.
	 */
	private USTCreatorFactory() {
	}

}
