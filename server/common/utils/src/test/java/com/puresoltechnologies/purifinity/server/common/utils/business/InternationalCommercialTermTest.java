package com.puresoltechnologies.purifinity.server.common.utils.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.common.utils.business.BusinessDuty;
import com.puresoltechnologies.purifinity.server.common.utils.business.BusinessParty;
import com.puresoltechnologies.purifinity.server.common.utils.business.InternationalCommercialTerm;

public class InternationalCommercialTermTest {

	@Test
	public void testForCompleteNames() {
		for (InternationalCommercialTerm term : InternationalCommercialTerm.class
				.getEnumConstants()) {
			String name = term.getDisplayName();
			assertNotNull(name);
			assertFalse(name.isEmpty());
			assertEquals(term.name(), name);
		}
	}

	/**
	 * Check that all names are present and that the correct names are applied
	 * to the enum constants.
	 */
	@Test
	public void testForCompleteLongNames() {
		for (InternationalCommercialTerm term : InternationalCommercialTerm.class
				.getEnumConstants()) {
			String name = term.getDisplayLongName(Locale.US);
			assertNotNull(name);
			assertFalse(name.isEmpty());
		}
	}

	/**
	 * Check that all names are present and that the correct names are applied
	 * to the enum constants.
	 */
	@Test
	public void testForCompleteDescriptions() {
		for (InternationalCommercialTerm term : InternationalCommercialTerm.class
				.getEnumConstants()) {
			String description = term.getDescription(Locale.US);
			assertNotNull(description);
			assertFalse(description.isEmpty());
		}
	}

	@Test
	public void testDutiesEXP() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			BusinessParty party = InternationalCommercialTerm.EXW
					.getResponsibleParty(duty);
			assertEquals(BusinessParty.BUYER, party);
		}
	}

	@Test
	public void testDutiesFCA() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			BusinessParty party = InternationalCommercialTerm.FCA
					.getResponsibleParty(duty);
			if (duty.ordinal() <= 2) {
				assertEquals(BusinessParty.SELLER, party);
			} else {
				assertEquals(BusinessParty.BUYER, party);
			}
		}
	}

	@Test
	public void testDutiesFAS() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			BusinessParty party = InternationalCommercialTerm.FAS
					.getResponsibleParty(duty);
			if (duty.ordinal() <= 3) {
				assertEquals(BusinessParty.SELLER, party);
			} else {
				assertEquals(BusinessParty.BUYER, party);
			}
		}
	}

	@Test
	public void testDutiesFOB() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			BusinessParty party = InternationalCommercialTerm.FOB
					.getResponsibleParty(duty);
			if (duty.ordinal() <= 4) {
				assertEquals(BusinessParty.SELLER, party);
			} else {
				assertEquals(BusinessParty.BUYER, party);
			}
		}
	}

	@Test
	public void testDutiesCFR() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			BusinessParty party = InternationalCommercialTerm.CFR
					.getResponsibleParty(duty);
			if (duty.ordinal() <= 5) {
				assertEquals(BusinessParty.SELLER, party);
			} else {
				assertEquals(BusinessParty.BUYER, party);
			}
		}
	}

	@Test
	public void testDutiesCIF() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			BusinessParty party = InternationalCommercialTerm.CIF
					.getResponsibleParty(duty);
			if ((duty.ordinal() <= 5) || (duty.ordinal() == 9)) {
				assertEquals(BusinessParty.SELLER, party);
			} else {
				assertEquals(BusinessParty.BUYER, party);
			}
		}
	}

	@Test
	public void testDutiesDAT() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			BusinessParty party = InternationalCommercialTerm.DAT
					.getResponsibleParty(duty);
			if (duty.ordinal() <= 6) {
				assertEquals(BusinessParty.SELLER, party);
			} else {
				assertEquals(BusinessParty.BUYER, party);
			}
		}
	}

	@Test
	public void testDutiesDAP() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			BusinessParty party = InternationalCommercialTerm.DAP
					.getResponsibleParty(duty);
			if (duty.ordinal() <= 8) {
				assertEquals(BusinessParty.SELLER, party);
			} else {
				assertEquals(BusinessParty.BUYER, party);
			}
		}
	}

	@Test
	public void testDutiesCPT() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			BusinessParty party = InternationalCommercialTerm.CPT
					.getResponsibleParty(duty);
			if (duty.ordinal() <= 8) {
				assertEquals(BusinessParty.SELLER, party);
			} else {
				assertEquals(BusinessParty.BUYER, party);
			}
		}
	}

	@Test
	public void testDutiesCIP() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			BusinessParty party = InternationalCommercialTerm.CIP
					.getResponsibleParty(duty);
			if (duty.ordinal() <= 9) {
				assertEquals(BusinessParty.SELLER, party);
			} else {
				assertEquals(BusinessParty.BUYER, party);
			}
		}
	}

	@Test
	public void testDutiesDDP() {
		for (BusinessDuty duty : BusinessDuty.class.getEnumConstants()) {
			BusinessParty party = InternationalCommercialTerm.DDP
					.getResponsibleParty(duty);
			if (duty.ordinal() != 9) {
				assertEquals(BusinessParty.SELLER, party);
			} else {
				assertEquals(BusinessParty.BUYER, party);
			}
		}
	}

}
