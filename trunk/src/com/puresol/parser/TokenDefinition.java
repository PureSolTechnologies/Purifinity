package com.puresol.parser;

import java.util.regex.Pattern;

public interface TokenDefinition {

    public Pattern getStartPattern();

    public Pattern getPattern();

    public TokenPublicity getPublicity();

    public boolean included(String string);

    public boolean atStart(String string);

    public String getTokenAtStart(String string);

    public String getIncludedToken(String string);
}
