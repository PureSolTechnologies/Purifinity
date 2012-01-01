package com.puresol.math.hp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.math.hp.IntervalException;
import com.puresol.math.hp.NewtonsMethod;
import com.puresol.utils.MethodInvokationException;

public class NewtonsMethodTest {

	public double y(double x) {
		return x;
	}

	@Test
	public void testConstructor() {
		try {
			NewtonsMethod newton = new NewtonsMethod(this, "y");
			assertSame(this, newton.getObject());
			assertEquals("y", newton.getMethodName());
		} catch (MethodInvokationException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testFunction() {
		try {
			NewtonsMethod newton = new NewtonsMethod(this, "y");
			for (double x = -10.0; x <= 10.0; x += 1.0) {
				assertEquals(y(x), newton.function(x), 1e-10);
			}
		} catch (MethodInvokationException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testFind() {
		try {
			NewtonsMethod newton = new NewtonsMethod(this, "y");
			for (double y = -10.0; y <= 10.0; y += 1.0) {
				double x = newton.find(y, -10.0, 10.0, 1e-9);
				assertEquals(y, y(x), 1e-8);
			}
		} catch (MethodInvokationException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (IntervalException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testIntervalException() {
		try {
			NewtonsMethod newton = new NewtonsMethod(this, "y");
			newton.find(-5, -8.0, -7, 1e-9);
			fail("An IntervalException was expected!");
		} catch (MethodInvokationException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (IntervalException e) {
			// this exception was expected!
		}
	}

}
