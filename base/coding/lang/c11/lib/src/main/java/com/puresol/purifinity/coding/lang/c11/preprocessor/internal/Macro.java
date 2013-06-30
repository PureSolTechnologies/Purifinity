package com.puresol.purifinity.coding.lang.c11.preprocessor.internal;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.grammar.token.Visibility;
import com.puresol.purifinity.uhura.lexer.Token;
import com.puresol.purifinity.uhura.lexer.TokenMetaData;
import com.puresol.purifinity.uhura.lexer.TokenStream;
import com.puresol.purifinity.uhura.source.UnspecifiedSourceCodeLocation;

/**
 * This class provides the information for a single defined preprocessor macro,
 * 
 * @author Rick-Rainer Ludwig
 */
public class Macro {

    private final String name;
    private final TokenStream replacement;
    private final List<String> parameters = new ArrayList<String>();
    private final boolean optionalParameters;

    public Macro(String name) {
	this.name = name;
	this.replacement = new TokenStream();
	this.replacement.add(createDefaultReplacementToken());
	optionalParameters = false;
    }

    public Macro(String name, TokenStream replacement) {
	this.name = name;
	this.replacement = replacement;
	if (this.replacement.size() == 0) {
	    replacement.add(createDefaultReplacementToken());
	}
	optionalParameters = false;
    }

    public Macro(String name, TokenStream replacement, List<String> parameters,
	    boolean optionalParameters) {
	this.name = name;
	this.replacement = replacement;
	if (this.replacement.size() == 0) {
	    replacement.add(createDefaultReplacementToken());
	}
	this.parameters.addAll(parameters);
	this.optionalParameters = optionalParameters;
    }

    private Token createDefaultReplacementToken() {
	TokenMetaData metaData = new TokenMetaData(
		new UnspecifiedSourceCodeLocation(), 1, 1);
	Token defaultReplacementToken = new Token("integer-constant", "1",
		Visibility.VISIBLE, metaData);
	return defaultReplacementToken;
    }

    public String getName() {
	return name;
    }

    public TokenStream getReplacement() {
	return replacement;
    }

    public List<String> getParameters() {
	return parameters;
    }

    public boolean isOptionalParameters() {
	return optionalParameters;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + (optionalParameters ? 1231 : 1237);
	result = prime * result
		+ ((parameters == null) ? 0 : parameters.hashCode());
	result = prime * result
		+ ((replacement == null) ? 0 : replacement.hashCode());
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
	Macro other = (Macro) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (optionalParameters != other.optionalParameters)
	    return false;
	if (parameters == null) {
	    if (other.parameters != null)
		return false;
	} else if (!parameters.equals(other.parameters))
	    return false;
	if (replacement == null) {
	    if (other.replacement != null)
		return false;
	} else if (!replacement.equals(other.replacement))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer(name);
	if ((parameters.size() > 0) || (optionalParameters)) {
	    buffer.append("(");
	    boolean first = true;
	    for (String parameter : parameters) {
		if (first) {
		    first = false;
		} else {
		    buffer.append(",");
		}
		buffer.append(parameter);
	    }
	    if (optionalParameters) {
		if (!first) {
		    buffer.append(",");
		}
		buffer.append("...");
	    }
	    buffer.append(")");
	}
	buffer.append(":\n");
	buffer.append(replacement);
	return buffer.toString();
    }
}
