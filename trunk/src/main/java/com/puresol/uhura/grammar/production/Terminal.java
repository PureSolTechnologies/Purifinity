package com.puresol.uhura.grammar.production;

import com.puresol.uhura.lexer.Token;

/**
 * This class represents a single terminal.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class Terminal extends AbstractConstruction {

	private static final long serialVersionUID = 9050440704073872898L;

	private final String text;
	private final int hashcode;
	private final boolean ignoreCase;

	public Terminal(String name, String text, boolean ignoreCase) {
		super(name, true);
		this.text = text;
		this.ignoreCase = ignoreCase;
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		hashcode = result;
	}

	public String getText() {
		return text;
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public boolean matches(Token token) {
		return matches(token.getName(), token.getText());
	}

	public boolean matches(Terminal terminal) {
		return matches(terminal.getName(), terminal.getText());
	}

	public boolean matches(String name, String text) {
		if (!getName().equals(name)) {
			return false;
		}
		if ((this.text == null) || (this.text.isEmpty())) {
			return true;
		}
		if (ignoreCase) {
			return this.text.equalsIgnoreCase(text);
		}
		return this.text.equals(text);
	}

	@Override
	public String toString() {
		if ((text == null) || (text.isEmpty())) {
			return super.toString();
		}
		return super.toString() + " '" + text + "'";
	}

	@Override
	public String toShortString() {
		if ((text == null) || (text.isEmpty())) {
			return super.toShortString();
		}
		return super.toShortString() + " '" + text + "'";
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Terminal other = (Terminal) obj;
		if (hashcode != other.hashcode) {
			return false;
		}
		if (ignoreCase) {
			if (text == null) {
				if (other.text != null)
					return false;
			} else if (!text.equalsIgnoreCase(other.text))
				return false;
		} else {
			if (text == null) {
				if (other.text != null)
					return false;
			} else if (!text.equals(other.text))
				return false;
		}
		return true;
	}

}
