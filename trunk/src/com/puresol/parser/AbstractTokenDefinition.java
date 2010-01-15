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

    private boolean isCaseSensitive;
    private Pattern pattern;
    private Pattern startPattern;
    private TokenPublicity publicity = TokenPublicity.VISIBLE;

    public AbstractTokenDefinition() {
	this.isCaseSensitive = true;
	compilePattern();
    }

    private void compilePattern() {
	if (isCaseSensitive) {
	    pattern =
		    Pattern.compile("(" + getPatternString()
			    + getLookAheadPatternString() + ")");
	    startPattern =
		    Pattern.compile("^(" + getPatternString()
			    + getLookAheadPatternString() + ")");
	} else {
	    pattern =
		    Pattern.compile("(" + getPatternString()
			    + getLookAheadPatternString() + ")",
			    Pattern.CASE_INSENSITIVE);
	    startPattern =
		    Pattern.compile("^(" + getPatternString()
			    + getLookAheadPatternString() + ")",
			    Pattern.CASE_INSENSITIVE);
	}
    }

    abstract public String getPatternString();

    abstract public String getLookAheadPatternString();

    protected void setCaseSensitive() {
	if (!isCaseSensitive) {
	    isCaseSensitive = true;
	    compilePattern();
	}
    }

    protected void setCaseInsensitive() {
	if (isCaseSensitive) {
	    isCaseSensitive = false;
	    compilePattern();
	}
    }

    public boolean isCaseSensitive() {
	return isCaseSensitive;
    }

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
    public boolean matches(String string) {
	return pattern.matcher(string).matches();
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (isCaseSensitive ? 1231 : 1237);
	result =
		prime * result
			+ ((pattern == null) ? 0 : pattern.hashCode());
	result =
		prime * result
			+ ((publicity == null) ? 0 : publicity.hashCode());
	result =
		prime
			* result
			+ ((startPattern == null) ? 0 : startPattern
				.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AbstractTokenDefinition other = (AbstractTokenDefinition) obj;
	if (isCaseSensitive != other.isCaseSensitive)
	    return false;
	if (pattern == null) {
	    if (other.pattern != null)
		return false;
	} else if (!pattern.equals(other.pattern))
	    return false;
	if (publicity == null) {
	    if (other.publicity != null)
		return false;
	} else if (!publicity.equals(other.publicity))
	    return false;
	if (startPattern == null) {
	    if (other.startPattern != null)
		return false;
	} else if (!startPattern.equals(other.startPattern))
	    return false;
	return true;
    }

    @Override
    public int compareTo(TokenDefinition other) {
	if (this.equals(other)) {
	    return 0;
	}
	if (other.atStart(this.getPatternString())) {
	    return -1;
	}
	if (this.atStart(((AbstractTokenDefinition) other)
		.getPatternString())) {
	    return 1;
	}
	return this.getPatternString().compareTo(
		((AbstractTokenDefinition) other).getPatternString());
    }
}
