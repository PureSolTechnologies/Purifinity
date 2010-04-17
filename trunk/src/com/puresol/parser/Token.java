package com.puresol.parser;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

/**
 * Token is a class for representation of a single string part of a input
 * stream. This token is used within TokenStream.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class Token implements Serializable {

	private static final long serialVersionUID = 1688116172490111060L;

	private static final Logger logger = Logger.getLogger(Token.class);

	public static Token createByDefinition(
			Class<? extends TokenDefinition> definition, int tokenID,
			int startPos, int startLine, String text) throws TokenException {
		try {
			return Token.createByDefinition(Instances
					.createInstance(definition), tokenID, startPos, startLine,
					text);
		} catch (ClassInstantiationException e) {
			logger.error(e.getMessage(), e);
			throw new TokenException(e.getMessage());
		}
	}

	public static Token createByDefinition(TokenDefinition definition,
			int tokenID, int startPos, int startLine, String text) {
		return new Token(tokenID, definition.getPublicity(), startPos,
				startLine, text, definition.getClass());
	}

	public static Token createWithNewID(Token token, int tokenID) {
		return new Token(tokenID, token.getPublicity(), token.getStartPos(),
				token.getStartLine(), token.getText(), token.getDefinition());
	}

	public static Token createWithNewPositions(Token token, int tokenID,
			int startPos, int startLine) {
		return new Token(tokenID, token.getPublicity(), startPos, startLine,
				token.getText(), token.getDefinition());
	}

	public static Token createPrimitiveFromString(int tokenID, int startPos,
			int startLine, String text) {
		return new Token(tokenID, TokenPublicity.VISIBLE, startPos, startLine,
				text, null);
	}

	private final int tokenID;
	private final int startPos;
	private final TokenPublicity publicity;
	private final String text;
	private final int startLine;
	private final int stopLine;
	private final Class<? extends TokenDefinition> definition;

	private Token(int tokenID, TokenPublicity publicity, int startPos,
			int startLine, String text,
			Class<? extends TokenDefinition> definition) {
		this.tokenID = tokenID;
		this.publicity = publicity;
		this.startPos = startPos;
		this.text = text;
		this.startLine = startLine;
		this.stopLine = startLine + getLineBreakCount(text);
		this.definition = definition;
	}

	private int getLineBreakCount(String text) {
		char[] chars = text.toCharArray();
		int count = 0;
		for (int index = 0; index < text.length(); index++) {
			if (chars[index] == '\n') {
				count++;
			}
		}
		return count;
	}

	public int getTokenID() {
		return tokenID;
	}

	public int getStartPos() {
		return startPos;
	}

	public int getLength() {
		return text.length();
	}

	public TokenPublicity getPublicity() {
		return publicity;
	}

	public String getText() {
		return text;
	}

	public int getStartLine() {
		return startLine;
	}

	public int getStopLine() {
		return stopLine;
	}

	public Class<? extends TokenDefinition> getDefinition() {
		return definition;
	}

	public TokenDefinition getDefinitionInstance() throws TokenException {
		try {
			return Instances.createInstance(definition);
		} catch (ClassInstantiationException e) {
			throw new TokenException(e.getMessage());
		}
	}

	public String toString() {
		String output = String.valueOf(tokenID) + " ";
		if (stopLine != startLine) {
			output += startLine + "-" + stopLine;
		} else {
			output += String.valueOf(startLine);
		}
		output += " (" + startPos + "/" + text.length() + "): '" + text + "'";
		if (definition != null) {
			output += " (" + definition.getSimpleName() + ")";
		}
		if (publicity == TokenPublicity.HIDDEN) {
			output += " (hidden!)";
		} else if (publicity == TokenPublicity.ADDED) {
			output += " (added!)";
		}
		return output;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((definition == null) ? 0 : definition.hashCode());
		result = prime * result
				+ ((publicity == null) ? 0 : publicity.hashCode());
		result = prime * result + startLine;
		result = prime * result + startPos;
		result = prime * result + stopLine;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + tokenID;
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
		Token other = (Token) obj;
		if (definition == null) {
			if (other.definition != null)
				return false;
		} else if (!definition.equals(other.definition))
			return false;
		if (publicity == null) {
			if (other.publicity != null)
				return false;
		} else if (!publicity.equals(other.publicity))
			return false;
		if (startLine != other.startLine)
			return false;
		if (startPos != other.startPos)
			return false;
		if (stopLine != other.stopLine)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (tokenID != other.tokenID)
			return false;
		return true;
	}
}
