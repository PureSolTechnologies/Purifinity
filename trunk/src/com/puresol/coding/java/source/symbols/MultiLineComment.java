package com.puresol.coding.java.source.symbols;

import com.puresol.coding.tokentypes.Comment;

public class MultiLineComment extends Comment {

    @Override
    public String getPatternString() {
	return "/\\*([^*]|\\*[^/])*\\*/";
    }

}
