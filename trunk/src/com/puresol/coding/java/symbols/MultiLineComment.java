package com.puresol.coding.java.symbols;

import com.puresol.coding.tokentypes.Comment;

public class MultiLineComment extends Comment {

    @Override
    public String getPatternString() {
	return "/\\*([^*]|\\*[^/])*\\*/";
    }

}
