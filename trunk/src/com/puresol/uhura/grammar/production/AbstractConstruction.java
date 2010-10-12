package com.puresol.uhura.grammar.production;

/**
 * THIS CLASS IS NOT THREAD SAFE!!!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractConstruction implements Construction {

	private static final long serialVersionUID = -8190479779174841005L;

	private final String name;
	private final String text;
	private final boolean isTerminal;
	private final int hashCode;

	public AbstractConstruction(String name, String text, boolean isTerminal) {
		super();
		this.name = name;
		this.text = text;
		this.isTerminal = isTerminal;
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + (isTerminal ? 1 : 0);
		hashCode = result;
	}

	/**
	 * @return the typeId
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#getText()
	 */
	@Override
	public String getText() {
		return text;
	}

	@Override
	public boolean isTerminal() {
		return isTerminal;
	}

	@Override
	public boolean isNonTerminal() {
		return !isTerminal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#toString()
	 */
	@Override
	public String toString() {
		if (isTerminal) {
			return name + ": '" + text + "' (TERMINAL)";
		} else {
			return name + ": '" + text + "' (NON-TERMINAL)";
		}
	}

	public String toShortString() {
		if (name.isEmpty()) {
			return "'" + text + "'";
		} else {
			return name;
		}
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractConstruction other = (AbstractConstruction) obj;
		if (this.hashCode != other.hashCode) {
			return false;
		}
		if (isTerminal != other.isTerminal)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public int compareTo(Construction other) {
		if (other == null) {
			return 1;
		}
		int result = this.getName().compareTo(other.getName());
		if (result != 0) {
			return result;
		}
		result = Boolean.valueOf(isTerminal()).compareTo(
				Boolean.valueOf(other.isTerminal()));
		if (result != 0) {
			return result;
		}
		return this.getText().compareTo(other.getText());
	}
}
