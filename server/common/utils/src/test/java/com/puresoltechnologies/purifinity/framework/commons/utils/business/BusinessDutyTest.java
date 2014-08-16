package com.puresoltechnologies.purifinity.framework.commons.utils.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.common.utils.business.BusinessDuty;

public class BusinessDutyTest {

	/**
	 * Check that all names are present and that the correct names are applied
	 * to the enum constants.
	 */
	@Test
	public void testForCompleteNames() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			String name = duty.getName(Locale.US);
			assertNotNull(name);
			assertFalse(name.isEmpty());
			name = name.replaceAll(" ", "_").toUpperCase()
					.replaceAll("_\\(.+\\)$", "").replaceAll("-", "_");
			assertEquals(duty.name(), name);
		}
	}

}
