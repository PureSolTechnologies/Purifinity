package com.puresoltechnologies.purifinity.evaluation.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class SeverityTest {

    @Test
    public void testMostSevereNone() {
	assertEquals(Severity.NONE, Severity.getMostSevere(Severity.NONE));
    }

    @Test
    public void testMostSevereTrivial() {
	assertEquals(Severity.TRIVIAL, Severity.getMostSevere(Severity.TRIVIAL, Severity.NONE));
	assertEquals(Severity.TRIVIAL, Severity.getMostSevere(Severity.TRIVIAL));
    }

    @Test
    public void testMostSevereMinor() {
	assertEquals(Severity.MINOR, Severity.getMostSevere(Severity.TRIVIAL, Severity.MINOR, Severity.NONE));
	assertEquals(Severity.MINOR, Severity.getMostSevere(Severity.MINOR, Severity.NONE));
	assertEquals(Severity.MINOR, Severity.getMostSevere(Severity.MINOR));
    }

    @Test
    public void testMostSevereMajor() {
	assertEquals(Severity.MAJOR,
		Severity.getMostSevere(Severity.TRIVIAL, Severity.MINOR, Severity.MAJOR, Severity.NONE));
	assertEquals(Severity.MAJOR, Severity.getMostSevere(Severity.MINOR, Severity.MAJOR, Severity.NONE));
	assertEquals(Severity.MAJOR, Severity.getMostSevere(Severity.MAJOR, Severity.NONE));
	assertEquals(Severity.MAJOR, Severity.getMostSevere(Severity.MAJOR));
    }

    @Test
    public void testMostSevereCritical() {
	assertEquals(Severity.CRITICAL, Severity.getMostSevere(Severity.TRIVIAL, Severity.MINOR, Severity.MAJOR,
		Severity.CRITICAL, Severity.NONE));
	assertEquals(Severity.CRITICAL,
		Severity.getMostSevere(Severity.MINOR, Severity.MAJOR, Severity.CRITICAL, Severity.NONE));
	assertEquals(Severity.CRITICAL, Severity.getMostSevere(Severity.MAJOR, Severity.CRITICAL, Severity.NONE));
	assertEquals(Severity.CRITICAL, Severity.getMostSevere(Severity.CRITICAL, Severity.NONE));
	assertEquals(Severity.CRITICAL, Severity.getMostSevere(Severity.CRITICAL));
    }

    @Test
    public void testMostSevereBlocker() {
	assertEquals(Severity.BLOCKER, Severity.getMostSevere(Severity.TRIVIAL, Severity.MINOR, Severity.MAJOR,
		Severity.CRITICAL, Severity.BLOCKER, Severity.NONE));
	assertEquals(Severity.BLOCKER, Severity.getMostSevere(Severity.MINOR, Severity.MAJOR, Severity.CRITICAL,
		Severity.BLOCKER, Severity.NONE));
	assertEquals(Severity.BLOCKER,
		Severity.getMostSevere(Severity.MAJOR, Severity.CRITICAL, Severity.BLOCKER, Severity.NONE));
	assertEquals(Severity.BLOCKER, Severity.getMostSevere(Severity.CRITICAL, Severity.BLOCKER, Severity.NONE));
	assertEquals(Severity.BLOCKER, Severity.getMostSevere(Severity.BLOCKER, Severity.NONE));
	assertEquals(Severity.BLOCKER, Severity.getMostSevere(Severity.BLOCKER));
    }
}
