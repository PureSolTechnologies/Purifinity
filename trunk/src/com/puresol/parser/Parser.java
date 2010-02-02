package com.puresol.parser;

public interface Parser {

    public void scan() throws PartDoesNotMatchException, ParserException;

    public int getNumberOfTokens();

    public int getCurrentPosition();

    public TokenStream getTokenStream();
}
