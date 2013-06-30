package com.puresol.purifinity.coding.lang.c11.preprocessor.internal;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.grammar.token.Visibility;
import com.puresol.purifinity.uhura.lexer.Token;
import com.puresol.purifinity.uhura.lexer.TokenStream;
import com.puresol.purifinity.uhura.preprocessor.PreprocessorException;

public class ReplacementParameterResult {

    /**
     * This method starts on a given position within a given {@link TokenStream}
     * and reads the parameters for the function like macro replacement.
     * 
     * @param tokenStream
     * @param tokenId
     * @return A {@link List} of {@link TokenStream} is returned in case of a
     *         successful read of the parameters. Null is returned if the search
     *         was not successful.
     * @throws PreprocessorException
     */
    public static ReplacementParameterResult extractParameterReplacements(
	    TokenStream tokenStream, int tokenId) throws PreprocessorException {
	ReplacementParameterResult parameters = new ReplacementParameterResult();
	int parenDepth = 0;
	/*
	 * The token at tokenId is the name of the macro. So we need to start
	 * with the token after it.
	 */
	for (int i = tokenId + 1; i < tokenStream.size(); i++) {
	    parameters.increaseTokensToSkip();
	    Token token = tokenStream.get(i);
	    if (token.getText().equals("(")) {
		if (parenDepth == 0) {
		    /*
		     * We found our first parameter.
		     */
		    parameters.addNewParameter();
		} else {
		    parameters.addTokenToCurrentParameter(token);
		}
		parenDepth++;
	    } else if (token.getText().equals(")")) {
		parenDepth--;
		if (parenDepth <= 0) {
		    /*
		     * We are finished. We either found
		     */
		    return parameters;
		}
		parameters.addTokenToCurrentParameter(token);
	    } else if (token.getText().equals(",") && parenDepth == 1) {
		/*
		 * We found a new parameter.
		 */
		parameters.addNewParameter();
	    } else {
		if (parameters.hasParameters() != null) {
		    parameters.addTokenToCurrentParameter(token);
		} else if (token.getVisibility() == Visibility.VISIBLE) {
		    Token startToken = tokenStream.get(tokenId);
		    throw new PreprocessorException(
			    "An opening parenthesis was expected after macro name '"
				    + startToken.getName() + "' in line "
				    + startToken.getMetaData().getLine()
				    + ", but was not found!");

		}
	    }
	}
	if (parenDepth > 0) {
	    /*
	     * We are not finished and we do not have more to read...
	     */
	    return null;
	}
	/*
	 * If we end up here, then we did not find any parenthesis and therefore
	 * no parameters.
	 */
	return parameters;
    }

    private final List<TokenStream> parameters = new ArrayList<TokenStream>();
    private TokenStream currentParameter = null;
    private int tokensToSkip = 0;

    private void addNewParameter() {
	currentParameter = new TokenStream();
	parameters.add(currentParameter);
    }

    private Object hasParameters() {
	return parameters.size() > 0;
    }

    private void addTokenToCurrentParameter(Token token) {
	currentParameter.add(token);
    }

    private void increaseTokensToSkip() {
	tokensToSkip++;
    }

    public int getTokensToSkip() {
	return tokensToSkip;
    }

    /**
     * This method returns the parameter replacement as {@link TokenStream}.
     * 
     * @param parameterId
     *            specifies the parameter id. This id is needs to a positive
     *            value.
     * @return The parameter replacement is returned as {@link TokenStream}. If
     *         the parameter id is larger than the number of parameters found,
     *         then an empty TokenStream is returned.
     */
    public TokenStream getReplacement(int parameterId) {
	if (parameterId < 0) {
	    throw new IllegalArgumentException(
		    "Parameter id must not be negative!");
	}
	if (parameterId < parameters.size()) {
	    return parameters.get(parameterId);
	}
	return new TokenStream();
    }

    /**
     * This method returns the number of currently defined parameters.
     * 
     * @return An int is returned containing the number of parameters.
     */
    public int getNumberOfParameters() {
	return parameters.size();
    }
}
