package com.puresol.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a class for an abstract keyword implementation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractTokenDefinition implements TokenDefinition {

    private Pattern pattern;
    private Pattern startPattern;
    private TokenPublicity publicity = TokenPublicity.VISIBLE;

    public AbstractTokenDefinition() {
	this.pattern = Pattern.compile("(" + getPatternString() + ")");
	this.startPattern =
		Pattern.compile("^(" + getPatternString() + ")");
    }

    abstract public String getPatternString();

    protected void setPublicity(TokenPublicity publicity) {
	this.publicity = publicity;
    }

    public TokenPublicity getPublicity() {
	return publicity;
    }

    @Override
    public Pattern getPattern() {
	return pattern;
    }

    @Override
    public Pattern getStartPattern() {
	return startPattern;
    }

    @Override
    public boolean atStart(String string) {
	return startPattern.matcher(string).find();
    }

    @Override
    public boolean included(String string) {
	return pattern.matcher(string).find();
    }

    @Override
    public String getTokenAtStart(String string) {
	Matcher matcher = startPattern.matcher(string);
	if (!matcher.find()) {
	    return "";
	}
	return matcher.group(0);
    }

    @Override
    public String getIncludedToken(String string) {
	Matcher matcher = pattern.matcher(string);
	if (!matcher.find()) {
	    return "";
	}
	return matcher.group(0);
    }
}
