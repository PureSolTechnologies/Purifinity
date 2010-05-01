package com.puresol.coding.lang.java.source.symbols;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.lang.java.source.symbols.TraditionalComment;

public class MultiLineCommentTest extends TestCase {

    @Test
    public void test() {
	TraditionalComment comment = new TraditionalComment();
	Assert.assertEquals("/*** Test */", comment
		.getTokenAtStart("/*** Test */ AAA /* Test */"));
    }

}
