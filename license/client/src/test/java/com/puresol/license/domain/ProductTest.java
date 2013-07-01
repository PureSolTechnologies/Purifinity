package com.puresol.license.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ProductTest {

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNameNull() {
		new Product(null, new Version(1, 2, 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNameEmptyString() {
		new Product("", new Version(1, 2, 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidVersionNull() {
		new Product("product", null);
	}

	@Test
	public void testFromString() {
		Product product = Product.fromString("product 1.2.3-4+5");
		assertNotNull(product);
		assertEquals("product", product.getName());
		assertEquals(Version.fromString("1.2.3-4+5"), product.getVersion());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringInvalidMissingVersion() {
		Product.fromString("product");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringInvalidMissingName() {
		Product.fromString("1.2.3-4+5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringInvalidTooManyParts() {
		Product.fromString("product 1.0.0 additionalPart");
	}

}
