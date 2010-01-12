package com.puresol.coding.java.source.tokens;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.java.source.symbols.MultiLineComment;

public class MultiLineCommentTest extends TestCase {

    @Test
    public void test() {
	MultiLineComment comment = new MultiLineComment();
	Assert.assertEquals("/*** Test */", comment
		.getTokenAtStart("/*** Test */ AAA /* Test */"));
    }

}
