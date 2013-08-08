package com.puresol.purifinity.uhura.ust;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Test;

public class USTCreatorFactoryTest {

	private final Package pkg = getClass().getPackage();

	@After
	public void initialize() {
		if (USTCreatorFactory.isInitialize()) {
			USTCreatorFactory.destroy();
		}
	}

	@Test
	public void testInitializeAndDestroy() {
		USTCreatorFactory.initialize();
		USTCreatorFactory.destroy();
	}

	@Test(expected = IllegalStateException.class)
	public void testCheckInitializationForDestroy() {
		USTCreatorFactory.destroy();
	}

	@Test(expected = IllegalStateException.class)
	public void testCheckInitializationForRegister()
			throws UniversalSyntaxTreeCreatorException {
		USTCreatorFactory.register(pkg);
	}

	@Test(expected = IllegalStateException.class)
	public void testCheckInitializationForUnregister() {
		USTCreatorFactory.unregister(pkg);
	}

	@Test(expected = IllegalStateException.class)
	public void testCheckInitializationForCreate() {
		USTCreatorFactory.create(pkg);
	}

	@Test(expected = IllegalStateException.class)
	public void testDoubleInitialize() {
		USTCreatorFactory.initialize();
		USTCreatorFactory.initialize();
	}

	@Test(expected = IllegalStateException.class)
	public void testDoubleDestroy() {
		USTCreatorFactory.initialize();
		USTCreatorFactory.destroy();
		USTCreatorFactory.destroy();
	}

	@Test
	public void testRegisterAndUnregister()
			throws UniversalSyntaxTreeCreatorException {
		USTCreatorFactory.initialize();
		USTCreatorFactory.register(pkg);
		USTCreatorFactory.unregister(pkg);
		USTCreatorFactory.destroy();
	}

	@Test(expected = IllegalStateException.class)
	public void testDoubleRegister() throws UniversalSyntaxTreeCreatorException {
		USTCreatorFactory.initialize();
		USTCreatorFactory.register(pkg);
		USTCreatorFactory.register(pkg);
		USTCreatorFactory.destroy();
	}

	@Test
	public void testDoubleUnregister()
			throws UniversalSyntaxTreeCreatorException {
		USTCreatorFactory.initialize();
		USTCreatorFactory.register(pkg);
		USTCreatorFactory.unregister(pkg);
		USTCreatorFactory.unregister(pkg);
		USTCreatorFactory.destroy();
	}

	@Test
	public void testCreate() throws UniversalSyntaxTreeCreatorException {
		USTCreatorFactory.initialize();
		USTCreatorFactory.register(pkg);
		USTCreator creator = USTCreatorFactory.create(pkg);
		assertNotNull(creator);
		USTCreatorFactory.unregister(pkg);
		USTCreatorFactory.destroy();
	}

	@Test
	public void testCreateUnregistered() {
		USTCreatorFactory.initialize();
		USTCreator creator = USTCreatorFactory.create(pkg);
		assertNull(creator);
		USTCreatorFactory.destroy();
	}
}
