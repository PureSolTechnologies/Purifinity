package com.puresol.parser;

import java.util.List;

import com.puresol.coding.analysis.CodeRange;

public interface Parser {

    public void scan() throws PartDoesNotMatchException, ParserException;

    public int getNumberOfTokens();

    public int getCurrentPosition();

    public TokenStream getTokenStream();

    public List<CodeRange> getCodeRanges();
}
