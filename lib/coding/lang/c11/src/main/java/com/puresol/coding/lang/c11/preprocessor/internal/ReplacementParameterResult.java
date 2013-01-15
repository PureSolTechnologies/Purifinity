package com.puresol.coding.lang.c11.preprocessor.internal;

import java.util.ArrayList;
import java.util.List;

import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.preprocessor.PreprocessorException;

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
	if (parenDepth > 1) {
	    /*
	     * We are not finished and we do not have more to read...
	     */
	    return null;
	}
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
}
