package com.puresol.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

public class Token {

	private static final Logger logger = Logger.getLogger(Token.class);

	private final int tokenID;
	private final int startPos;
	private final int length;
	private final TokenPublicity publicity;
	private final String text;
	private final int startLine;
	private final int stopLine;
	private final Class<? extends TokenDefinition> definition;

	public Token(int tokenID, TokenPublicity publicity, int startPos,
			int length, String text, int startLine, int stopLine,
			Class<? extends TokenDefinition> definition) {
		this.tokenID = tokenID;
		this.publicity = publicity;
		this.startPos = startPos;
		this.length = length;
		this.text = text;
		this.startLine = startLine;
		this.stopLine = stopLine;
		this.definition = definition;
	}

	public int getTokenID() {
		return tokenID;
	}

	public int getStartPos() {
		return startPos;
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

	public TokenDefinition getDefinitionInstance() {
		try {
			Constructor<? extends TokenDefinition> constructor = definition
					.getConstructor();
			return (TokenDefinition) constructor.newInstance();
		} catch (IllegalArgumentException e) {
			logger.warn(e.getMessage(), e);
		} catch (InstantiationException e) {
			logger.warn(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.warn(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		} catch (SecurityException e) {
			logger.warn(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			logger.warn(e.getMessage(), e);
		}
		return null;
	}

	public String toString() {
		String output = String.valueOf(tokenID) + " ";
		if (stopLine != startLine) {
			output += startLine + "-" + stopLine;
		} else {
			output += String.valueOf(startLine);
		}
		output += " (" + startPos + "/" + length + "): '" + text + "'";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((definition == null) ? 0 : definition.hashCode());
		result = prime * result + length;
		result = prime * result
				+ ((publicity == null) ? 0 : publicity.hashCode());
		result = prime * result + startLine;
		result = prime * result + startPos;
		result = prime * result + stopLine;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + tokenID;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		if (length != other.length)
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
