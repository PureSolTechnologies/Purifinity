package com.puresol.parser;

public interface Part {

    public void scan() throws PartDoesNotMatchException;

    public int getNumberOfTokens();

    public int getCurrentPosition();

    public TokenStream getTokenStream();
}
