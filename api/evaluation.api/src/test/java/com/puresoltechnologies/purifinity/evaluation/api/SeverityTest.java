package com.puresoltechnologies.purifinity.evaluation.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class SeverityTest {

    @Test
    public void testMostSevere() {
	assertEquals(Severity.MINOR,
		Severity.getMostSevere(Severity.MINOR, Severity.MAJOR, Severity.CRITICAL, Severity.NONE));
	assertEquals(Severity.MINOR, Severity.getMostSevere(Severity.MINOR, Severity.CRITICAL, Severity.NONE));
	assertEquals(Severity.MAJOR, Severity.getMostSevere(Severity.MAJOR, Severity.CRITICAL, Severity.NONE));
	assertEquals(Severity.CRITICAL, Severity.getMostSevere(Severity.CRITICAL, Severity.NONE));
    }
}
