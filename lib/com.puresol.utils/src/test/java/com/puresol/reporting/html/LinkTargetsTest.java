package com.puresol.reporting.html;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LinkTargetsTest {

    @Test
    public void testKeyword() {
	assertEquals("", LinkTarget.DEFAULT.getKeyword());
	assertEquals("_blank", LinkTarget.BLANK.getKeyword());
	assertEquals("_self", LinkTarget.SELF.getKeyword());
	assertEquals("_top", LinkTarget.TOP.getKeyword());
    }

}
