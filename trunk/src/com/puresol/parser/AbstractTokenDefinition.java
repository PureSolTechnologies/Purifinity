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

    private boolean isCaseSensitive = true;
    private Pattern pattern = null;
    private Pattern startPattern = null;
    private String patternString = "";
    private String lookAheadPatternString = "";
    private TokenPublicity publicity = TokenPublicity.VISIBLE;

    public AbstractTokenDefinition() {
	initialize();
	compilePattern();
    }

    abstract protected void initialize();

    private void compilePattern() {
	if (isCaseSensitive) {
	    pattern =
		    Pattern.compile("(" + patternString
			    + lookAheadPatternString + ")");
	    startPattern =
		    Pattern.compile("^(" + patternString
			    + lookAheadPatternString + ")");
	} else {
	    pattern =
		    Pattern.compile("(" + patternString
			    + lookAheadPatternString + ")",
			    Pattern.CASE_INSENSITIVE);
	    startPattern =
		    Pattern.compile("^(" + patternString
			    + lookAheadPatternString + ")",
			    Pattern.CASE_INSENSITIVE);
	}
    }

    protected final void setPatternString(String pattern) {
	this.patternString = pattern;
    }

    public final String getPatternString() {
	return patternString;
    }

    protected final void setLookAheadPatternString(String pattern) {
	this.lookAheadPatternString = pattern;
    }

    public final String getLookAheadPatternString() {
	return lookAheadPatternString;
    }

    protected final void setCaseSensitive() {
	if (!isCaseSensitive) {
	    isCaseSensitive = true;
	    compilePattern();
	}
    }

    protected final void setCaseInsensitive() {
	if (isCaseSensitive) {
	    isCaseSensitive = false;
	    compilePattern();
	}
    }

    public final boolean isCaseSensitive() {
	return isCaseSensitive;
    }

    protected final void setPublicity(TokenPublicity publicity) {
	this.publicity = publicity;
    }

    public final TokenPublicity getPublicity() {
	return publicity;
    }

    @Override
    public final Pattern getPattern() {
	return pattern;
    }

    @Override
    public final Pattern getStartPattern() {
	return startPattern;
    }

    @Override
    public final boolean atStart(String string) {
	return startPattern.matcher(string).find();
    }

    @Override
    public final boolean included(String string) {
	return pattern.matcher(string).find();
    }

    @Override
    public final boolean matches(String string) {
	return pattern.matcher(string).matches();
    }

    @Override
    public final String getTokenAtStart(String string) {
	Matcher matcher = startPattern.matcher(string);
	if (!matcher.find()) {
	    return "";
	}
	return matcher.group(0);
    }

    @Override
    public final String getIncludedToken(String string) {
	Matcher matcher = pattern.matcher(string);
	if (!matcher.find()) {
	    return "";
	}
	return matcher.group(0);
    }

    @Override
    public final int hashCode() {
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
    public final boolean equals(Object obj) {
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
	} else if (!pattern.toString().equals(other.pattern.toString()))
	    return false;
	if (publicity == null) {
	    if (other.publicity != null)
		return false;
	} else if (!publicity.equals(other.publicity))
	    return false;
	if (startPattern == null) {
	    if (other.startPattern != null)
		return false;
	} else if (!startPattern.toString().equals(
		other.startPattern.toString()))
	    return false;
	return true;
    }

    @Override
    public final int compareTo(TokenDefinition other) {
	if (this.equals(other)) {
	    return 0;
	}
	if (other.atStart(this.patternString)) {
	    return -1;
	}
	if (this.atStart(((AbstractTokenDefinition) other).patternString)) {
	    return 1;
	}
	return this.patternString
		.compareTo(((AbstractTokenDefinition) other).patternString);
    }
}
