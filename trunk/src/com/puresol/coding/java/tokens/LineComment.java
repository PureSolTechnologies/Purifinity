package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Comment;

public class LineComment extends Comment {

    @Override
    public String getPatternString() {
	return "//[^\\n]*";
    }

}
