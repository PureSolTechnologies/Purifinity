package com.puresol.coding.java.tokens;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

public class MultiLineCommentTest extends TestCase {

    @Test
    public void test() {
	MultiLineComment comment = new MultiLineComment();
	Assert.assertEquals("/*** Test */", comment
		.getTokenAtStart("/*** Test */ AAA /* Test */"));
    }

}
