package com.puresol.coding.lang.java.source.symbols;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class GreaterThanGreaterThanGreaterThanTest extends TestCase {

	@Test
	public void test() {
		GreaterThanGreaterThanGreaterThan token = new GreaterThanGreaterThanGreaterThan();
		Assert.assertTrue(token.atStart(">>>"));
		Assert.assertTrue(token.included(">>>"));
	}

}
