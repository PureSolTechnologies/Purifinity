package com.puresol.parser.tokens;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;
import com.puresol.utils.TextUtils;

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
			int startPos, int startLine, String text)
			throws TokenCreationException {
		try {
			return Token.createByDefinition(
					Instances.createInstance(definition), tokenID, startPos,
					startLine, text);
		} catch (ClassInstantiationException e) {
			logger.error(e.getMessage(), e);
			throw new TokenCreationException(e.getMessage());
		}
	}

	public static Token createByDefinition(TokenDefinition definition,
			int tokenID, int startPos, int startLine, String text) {
		return new Token(tokenID, definition.getPublicity(), startPos,
				startLine, text, definition.getClass());
	}

	public static Token createWithNewID(Token token, int tokenID) {
		return new Token(tokenID, token.getPublicity(), token.getPosition(),
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
	private final int position;
	private final TokenPublicity publicity;
	private final String text;
	private final int length;
	private final int startLine;
	private final int stopLine;
	private final Class<? extends TokenDefinition> definition;

	private Token(int tokenID, TokenPublicity publicity, int position,
			int startLine, String text,
			Class<? extends TokenDefinition> definition) {
		this.tokenID = tokenID;
		this.publicity = publicity;
		this.position = position;
		this.text = text;
		this.length = text.length();
		this.startLine = startLine;
		this.stopLine = startLine + TextUtils.countLineBreaks(text);
		this.definition = definition;
	}

	public int getTokenID() {
		return tokenID;
	}

	public int getPosition() {
		return position;
	}

	public int getLength() {
		return length;
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

	public TokenDefinition getDefinitionInstance()
			throws TokenCreationException {
		try {
			return Instances.createInstance(definition);
		} catch (ClassInstantiationException e) {
			throw new TokenCreationException(e.getMessage());
		}
	}

	public String toString() {
		StringBuffer output = new StringBuffer();
		output.append("id ");
		output.append(tokenID);
		output.append(" / line(s) ");
		if (stopLine != startLine) {
			output.append(startLine);
			output.append("-");
			output.append(stopLine);
		} else {
			output.append(startLine);
		}
		output.append(" (");
		output.append(position);
		output.append("/");
		output.append(text.length());
		output.append("): '");
		output.append(text);
		output.append("'");
		if (definition != null) {
			output.append(" (");
			output.append(definition.getSimpleName());
			output.append(")");
		}
		output.append(" (");
		output.append(publicity.name());
		output.append(")");
		return output.toString();
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
		result = prime * result + position;
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
		if (position != other.position)
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
